package com.example.finalhealty.ui.inicio;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.ShowToast;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {
    private SharedPreferences sp;
    private MutableLiveData<Integer> error;
    private MutableLiveData<String> token;
    private MutableLiveData<Usuario> usuarioMutableLiveData;
    private Context context;

    public MainViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        sp= context.getSharedPreferences("token",0);

    }

    public LiveData<Integer> getError(){
        if(error==null){
            error=new MutableLiveData<>();
        }
        return error;
    }

    public MutableLiveData<String> getToken(){
        if(token==null){
            token=new MutableLiveData<>();
        }
        return token;
    }

    public MutableLiveData<Usuario> getUsuarioMutableLiveData(){
        if(usuarioMutableLiveData==null){
            usuarioMutableLiveData=new MutableLiveData<>();
        }
        leer();
        return usuarioMutableLiveData;
    }

// >>>>>>>>>>>>>>>>>>>TRUE METHOD<<<<<<<<<<<<<<<<
   public void ingresar(String mail, String password){

        Call<String> dato= ApiClient.getMyApiClient().login(mail,password);

        dato.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {
                    token.postValue(response.body());
                    sp = context.getSharedPreferences("token", 0);
                    SharedPreferences.Editor editor = sp.edit();
                    String t = "Bearer " + response.body();
                    editor.putString("token", t);
                    editor.commit();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });
    }

    public void leer(){

        String accesToken = sp.getString("token","");
        Call<Usuario> usuarioCall= ApiClient.getMyApiClient().leer(accesToken);
        usuarioCall.enqueue(new Callback<Usuario>() {
                                @Override
                                public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                                    if(response.isSuccessful()){
                                        usuarioMutableLiveData.postValue(response.body());}
                                }

                                @Override
                                public void onFailure(Call<Usuario> call, Throwable t) {
                                }
                            }
        );
    }
}
