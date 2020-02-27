package com.example.finalhealty.administrador.ListarEventos;

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

public class ListarEventosViewModel  extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Evento>> eventoMLD;
    private SharedPreferences sp;
    private String token;
    private List<Evento> eventos;

    public ListarEventosViewModel(@NonNull Application application) {
        super(application);

        context=application.getApplicationContext();
        sp=context.getSharedPreferences("token",0);
        token=sp.getString("token","");
    }

    public LiveData<List<Evento>> getEventosMLD(){
        if(eventoMLD==null){
            eventoMLD=new MutableLiveData<>();
        }
        return eventoMLD;
    }

    public void obtenerEventos(){
        Call<List<Evento>> dato= ApiClient.getMyApiClient().getEventos(token);
        dato.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if(!response.body().isEmpty()&&getEventosMLD()!=null){
                    eventos=new ArrayList<>();
                    for(Evento m: response.body()){
                        eventos.add(m);
                    }
                    eventoMLD.postValue(eventos);
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });
    }
}