package com.example.finalhealty.ui.medallas;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.ShowToast;
import com.example.finalhealty.model.MedallaVirtual;
import com.example.finalhealty.request.ApiClient;
import com.example.finalhealty.ui.inicio.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedallasViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<MedallaVirtual>> medallasMLD;
    private SharedPreferences sp;
    private String token;
    private List<MedallaVirtual> obtenidas;

    public MedallasViewModel(@NonNull Application application) {
        super(application);

        context=application.getApplicationContext();
        sp=context.getSharedPreferences("token",0);
        token=sp.getString("token","");
    }

    public MutableLiveData<List<MedallaVirtual>> getMedallasMLD(){
        if(medallasMLD==null){
            medallasMLD=new MutableLiveData<>();
        }
        return medallasMLD;
    }

    public void obtener(){
        Call<List<MedallaVirtual>> dato= ApiClient.getMyApiClient().misMedallas(token, MainActivity.usuarioReal.getId());
        dato.enqueue(new Callback<List<MedallaVirtual>>() {
            @Override
            public void onResponse(Call<List<MedallaVirtual>> call, Response<List<MedallaVirtual>> response) {
                if(!response.body().isEmpty()&&getMedallasMLD()!=null){
                    obtenidas=new ArrayList<>();
                    for(MedallaVirtual m: response.body()){
                        obtenidas.add(m);
                    }
                    medallasMLD.postValue(obtenidas);
                }
            }

            @Override
            public void onFailure(Call<List<MedallaVirtual>> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });

    }
}