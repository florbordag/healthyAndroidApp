package com.example.finalhealty.coordinador.ui.coordmain.eventos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.ShowToast;
import com.example.finalhealty.model.Evento;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CordEventoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Evento>> misEventosMutableLiveData;
    private String token;

    private List<Evento> misEventos;

    private SharedPreferences sp;

    public CordEventoViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        sp=context.getSharedPreferences("token",0);
        token=sp.getString("token","");
    }

    public LiveData<List<Evento>> getEventosMTL(){
        if(misEventosMutableLiveData==null){
            misEventosMutableLiveData=new MutableLiveData<>();
        }
        return misEventosMutableLiveData;
    }

    public void obtenerMisEventos(){
        Call<List<Evento>> dato= ApiClient.getMyApiClient().getMisEventosCoord(token);
        dato.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if(!response.body().isEmpty()){
                    misEventos=new ArrayList<>();
                    for (Evento e: response.body()) {
                        misEventos.add(e);
                    }
                    if(getEventosMTL()!=null){
                        misEventosMutableLiveData.postValue(misEventos);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });
    }

    public void crearEvento(Evento e){
        Call<Evento> dato=ApiClient.getMyApiClient().crearEvento(token,e.getTitulo(),e.getDescripcion(),e.getFechaHora(),e.getActividadId());
        dato.enqueue(new Callback<Evento>() {
            @Override
            public void onResponse(Call<Evento> call, Response<Evento> response) {
                new ShowToast(context,"El evento se creo con éxito");
                obtenerMisEventos();
            }

            @Override
            public void onFailure(Call<Evento> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });
    }
}
