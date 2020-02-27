package com.example.finalhealty.administrador.ListarActividades;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.ShowToast;
import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarActividadesViewModel  extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Actividad>> actividadesMLD;
    private SharedPreferences sp;
    private String token;
    private List<Actividad> actividads;

    public ListarActividadesViewModel(@NonNull Application application) {
        super(application);

        context=application.getApplicationContext();
        sp=context.getSharedPreferences("token",0);
        token=sp.getString("token","");
    }

    public LiveData<List<Actividad>> getActividadesMLD(){
        if(actividadesMLD==null){
            actividadesMLD=new MutableLiveData<>();
        }
        return actividadesMLD;
    }

    public void obtenerActividades(){
        Call<List<Actividad>> dato= ApiClient.getMyApiClient().getActividades(token);
        dato.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                if(!response.body().isEmpty()&&getActividadesMLD()!=null){
                    actividads=new ArrayList<>();
                    for(Actividad m: response.body()){
                        actividads.add(m);
                    }
                    actividadesMLD.postValue(actividads);
                }
            }
            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });
    }


}