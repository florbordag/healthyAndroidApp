package com.example.finalhealty.ui.eventos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.ShowToast;
import com.example.finalhealty.model.Evento;
import com.example.finalhealty.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventosViewModel extends AndroidViewModel {
    private Context context;
    private String token;

    private List<Evento> misEventos=new ArrayList<>();
    private List<Evento> eventosDisponibles=new ArrayList<>();

    private MutableLiveData<List<Evento>> miEventoMutableLiveData;
    private MutableLiveData<List<Evento>> suEventoMutableLiveData;

    private SharedPreferences sp;

    public EventosViewModel(@NonNull Application application) {
        super(application);

        context=application.getApplicationContext();
        sp=context.getSharedPreferences("token",0);
        token=sp.getString("token","");

    }

    public LiveData<List<Evento>> getMisEventosMLD(){
        if(miEventoMutableLiveData==null){
            miEventoMutableLiveData=new MutableLiveData<>();
        }
        return miEventoMutableLiveData;
    }

    public LiveData<List<Evento>> getSusEventosMLD(){
        if(suEventoMutableLiveData==null){
            suEventoMutableLiveData=new MutableLiveData<>();
        }
        return suEventoMutableLiveData;
    }

    public void obtenerMisEventos(){
        Call<List<Evento>> dato= ApiClient.getMyApiClient().getMisEventos(token);
        dato.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if(!response.body().isEmpty()){
                    misEventos=new ArrayList<>();
                    for (Evento e: response.body()) {

                        misEventos.add(e);
                    }
                    if(getMisEventosMLD()!=null){
                        miEventoMutableLiveData.postValue(misEventos);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });
    }
    public void obtenerEventosDisponibes (){
        Call<List<Evento>> dato= ApiClient.getMyApiClient().getEventosDisponibles(token);
        dato.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if(!response.body().isEmpty()){
                    eventosDisponibles=new ArrayList<>();
                    for (Evento e: response.body()) {

                        eventosDisponibles.add(e);
                    }
                    if(getSusEventosMLD()!=null){
                        suEventoMutableLiveData.postValue(eventosDisponibles);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });
    }

    public void participar(final Evento evento){
        Call<Evento> dato= ApiClient.getMyApiClient().inscribirse(token,evento.getId());
        dato.enqueue(new Callback<Evento>() {
            @Override
            public void onResponse(Call<Evento> call, Response<Evento> response) {
                if(response.isSuccessful()){
                    obtenerEventosDisponibes();
                    obtenerMisEventos();
                    new ShowToast(context,"Se inscribió al evento "+evento.getTitulo());
                }
            }
            @Override
            public void onFailure(Call<Evento> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });

    }

    public void abandonar(Evento evento){
        //TODO agregue su abandono aqui
    }
}
