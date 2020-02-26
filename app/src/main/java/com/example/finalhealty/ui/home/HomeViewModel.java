package com.example.finalhealty.ui.home;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.model.Evento;
import com.example.finalhealty.request.ApiClient;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {
    private List<Actividad> misActividades=new ArrayList<>();
    private List<Evento> misEventos=new ArrayList<>();

    private MutableLiveData<List<Actividad>> miActividadMutableLiveData;
    private MutableLiveData<List<Evento>> miEventoMutableLiveData;

    private Context context;
    private SharedPreferences sp;


    public HomeViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        sp=context.getSharedPreferences("token",0);

    }

    public LiveData<List<Actividad>> getMiActividadMutableLiveData(){
        if(miActividadMutableLiveData==null){
            miActividadMutableLiveData=new MutableLiveData<>();
        }
        return miActividadMutableLiveData;
    }

    public LiveData<List<Evento>> getMisEventosMLD(){
        if(miEventoMutableLiveData==null){
            miEventoMutableLiveData=new MutableLiveData<>();
        }
        return miEventoMutableLiveData;
    }

    public void obtenerMisActividades() {
        Call<List<Actividad>> dato= ApiClient.getMyApiClient().getMisActividades(sp.getString("token",""));
        dato.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                if(!response.body().isEmpty()){
                    for (Actividad a: response.body()) {
                        misActividades.add(a);
                    }
                    Log.d("tamaño",misActividades.size()+"");
                    miActividadMutableLiveData.postValue(misActividades);
                }
            }

            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }

    public void obtenerMisEventos(){
        Call<List<Evento>> dato= ApiClient.getMyApiClient().getMisEventos(sp.getString("token",""));
        dato.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if(!response.body().isEmpty()){
                    misEventos=new ArrayList<>();
                    for (Evento e: response.body()) {

                        misEventos.add(e);
                    }
                    miEventoMutableLiveData.postValue(misEventos);
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {

            }
        });
    }

}