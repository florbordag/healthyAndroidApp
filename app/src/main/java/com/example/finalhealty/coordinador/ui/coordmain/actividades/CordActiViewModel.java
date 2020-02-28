package com.example.finalhealty.coordinador.ui.coordmain.actividades;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

public class CordActiViewModel extends AndroidViewModel {
    private Context context;
    private String token;
    private SharedPreferences sp;

    private List<Actividad> misActividades;
    private List<Actividad> dispoActividades;

    private MutableLiveData<List<Actividad>> misActividadesLD;
    private MutableLiveData<List<Actividad>> susActividadesLD;

    public CordActiViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        sp=context.getSharedPreferences("token",0);
        token=sp.getString("token","");

    }

    public LiveData<List<Actividad>> getMisActividadesMLD(){
        if(misActividadesLD==null){
            misActividadesLD=new MutableLiveData<>();
        }
        return misActividadesLD;
    }

    public LiveData<List<Actividad>> getSusActividadesMLD(){
        if(susActividadesLD==null){
            susActividadesLD=new MutableLiveData<>();
        }
        return susActividadesLD;
    }

    public void obtenerMisActividades(){
        Call<List<Actividad>> dato= ApiClient.getMyApiClient().getMisActividadesCoord(token);
        dato.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                if(!response.body().isEmpty()){
                    misActividades=new ArrayList<>();
                    for (Actividad a: response.body()) {
                        misActividades.add(a);
                    }
                    if(getMisActividadesMLD()!=null){
                        misActividadesLD.postValue(misActividades);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });
    }

    public void obtenerSusActividades(){
        Call<List<Actividad>> dato= ApiClient.getMyApiClient().getActividadesDisponiblesCoord(token);
        dato.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                if(!response.body().isEmpty()){
                    dispoActividades=new ArrayList<>();
                    for (Actividad a: response.body()) {
                        dispoActividades.add(a);
                    }
                    if(getSusActividadesMLD()!=null){
                        susActividadesLD.postValue(dispoActividades);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });
    }

    public void eliminarActividad(){

    }

    public void crearActividad(Actividad a){
        Call<Actividad> dato=ApiClient.getMyApiClient().crearActividad(token,a);
        dato.enqueue(new Callback<Actividad>() {
            @Override
            public void onResponse(Call<Actividad> call, Response<Actividad> response) {
                if(response.isSuccessful()){
                    new ShowToast(context,"La actividad se creo con éxito");
                    obtenerMisActividades();
                }
            }

            @Override
            public void onFailure(Call<Actividad> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });
    }

    public void retomarActividad(final Actividad actividad){
        Call<Actividad> dato=ApiClient.getMyApiClient().retomar(token,actividad.getId(),actividad);
        dato.enqueue(new Callback<Actividad>() {
            @Override
            public void onResponse(Call<Actividad> call, Response<Actividad> response) {
                if(response.isSuccessful()){
                    new ShowToast(context,"Retomaste la actividad "+
                            actividad.getTitulo()+" con éxito");
                    obtenerMisActividades();
                }
            }

            @Override
            public void onFailure(Call<Actividad> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });

    }


}
