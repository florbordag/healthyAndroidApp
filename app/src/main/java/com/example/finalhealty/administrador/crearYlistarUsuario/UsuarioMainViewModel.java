package com.example.finalhealty.administrador.crearYlistarUsuario;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.ShowToast;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioMainViewModel extends AndroidViewModel {
    private Context context;
    private String token;
    private SharedPreferences sp;

    private MutableLiveData<List<Usuario>> usuarioMutableLiveData;

    public UsuarioMainViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        sp=context.getSharedPreferences("token",0);
        token=sp.getString("token","");
    }


    public LiveData<List<Usuario>> getUsuariosMLD(){
        if(usuarioMutableLiveData==null){
            usuarioMutableLiveData=new MutableLiveData<>();
        }
        return usuarioMutableLiveData;
    }

    public void crearUSuario(Usuario u){
        Call<Usuario> dato= ApiClient.getMyApiClient().crearUsuario(token,u);
        dato.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    new ShowToast(context,"El usuario se creo con éxito");
                    //obtenerMisActividades();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });

    }
}
