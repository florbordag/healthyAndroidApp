package com.example.finalhealty.ui.actividades;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Telephony;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.ShowToast;
import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.model.Evento;
import com.example.finalhealty.model.Participante;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.request.ApiClient;
import com.example.finalhealty.ui.home.HomeViewModel;
import com.example.finalhealty.ui.inicio.MainActivity;
import com.example.finalhealty.ui.inicio.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadesViewModel extends AndroidViewModel {
    private Context context;
    private String token;
    private int participa;

    private List<Actividad> misActividades=new ArrayList<>();
    private List<Actividad> dispoActividades=new ArrayList<>();

    private MutableLiveData<List<Actividad>> miActividadMutableLiveData;
    private MutableLiveData<List<Actividad>> suActividadMutableLiveData;

    private SharedPreferences sp;

    public ActividadesViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();
        sp=context.getSharedPreferences("token",0);
        token=sp.getString("token","");
    }

    public LiveData<List<Actividad>> getMiActividadMutableLiveData(){
        if(miActividadMutableLiveData==null){
            miActividadMutableLiveData=new MutableLiveData<>();
        }
        return miActividadMutableLiveData;
    }

    public LiveData<List<Actividad>> getSuActividadMutableLiveData(){
        if(suActividadMutableLiveData==null){
            suActividadMutableLiveData=new MutableLiveData<>();
        }
        return suActividadMutableLiveData;
    }

    public void obtenerMisActividades() {
        Call<List<Actividad>> dato= ApiClient.getMyApiClient().getMisActividades(token);
        dato.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                if(!response.body().isEmpty()){
                    for (Actividad a: response.body()) {
                        misActividades.add(a);
                    }
                    if(getMiActividadMutableLiveData()!=null){
                        miActividadMutableLiveData.postValue(misActividades);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }

    public void obtenerActividadesDisponibles() {
        Call<List<Actividad>> dato= ApiClient.getMyApiClient().getActividadesDisponibles(token);
        dato.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                if(!response.body().isEmpty()){
                    for (Actividad a: response.body()) {
                        dispoActividades.add(a);
                    }
                    if(getSuActividadMutableLiveData()!=null){
                        suActividadMutableLiveData.postValue(dispoActividades);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }

    public void participar(Actividad actividad){
        Call<Actividad> dato= ApiClient.getMyApiClient().participar(token,actividad.getId());
        dato.enqueue(new Callback<Actividad>() {
            @Override
            public void onResponse(Call<Actividad> call, Response<Actividad> response) {
                if(response.isSuccessful()){
                    obtenerActividadesDisponibles();
                }
            }

            @Override
            public void onFailure(Call<Actividad> call, Throwable t) {

            }
        });
    }

    //Hacer menos lento
    public void abandonar(Actividad actividad){
        Call<Integer> id=ApiClient.getMyApiClient().idParticipante(token, MainActivity.usuarioReal.getId(),actividad.getId());
        id.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                participa=response.body();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
        Call<Participante> dato= ApiClient.getMyApiClient().abandonar(token,participa,0);
        dato.enqueue(new Callback<Participante>() {
            @Override
            public void onResponse(Call<Participante> call, Response<Participante> response) {
                if(response.isSuccessful()){
                    obtenerMisActividades();
                }
            }

            @Override
            public void onFailure(Call<Participante> call, Throwable t) {

            }
        });
    }


}

