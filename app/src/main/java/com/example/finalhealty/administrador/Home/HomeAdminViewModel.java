package com.example.finalhealty.administrador.Home;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.finalhealty.ShowToast;
import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.model.Evento;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.request.ApiClient;
import com.example.finalhealty.ui.inicio.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeAdminViewModel extends AndroidViewModel {

    private Context context;
    private String token;
    private SharedPreferences sp;

    private MutableLiveData<List<Usuario>> todosMLD;
    private MutableLiveData<List<Actividad>> actividadesMLD;
    private MutableLiveData<List<Evento>> eventosMLD;

    private List<Usuario> usuarios;
    private List<Actividad> actividades;
    private List<Evento> eventos;


    public HomeAdminViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        sp=context.getSharedPreferences("token",0);
        token=sp.getString("token","");

    }

    public LiveData<List<Usuario>> getTodosMLD() {
        if(todosMLD==null) {
            todosMLD=new MutableLiveData<>();
        }
        return todosMLD;
    }

    public LiveData<List<Actividad>> getActividadesMLD(){
        if(actividadesMLD==null){
            actividadesMLD=new MutableLiveData<>();
        }
        return actividadesMLD;
    }

    public LiveData<List<Evento>> getEventoMLD(){
        if(eventosMLD==null){
            eventosMLD=new MutableLiveData<>();
        }
        return eventosMLD;
    }

    public void cantidadUsuarios(){
        Call<List<Usuario>> dato= ApiClient.getMyApiClient().todes(token);
        dato.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(!response.body().isEmpty()&&getTodosMLD()!=null){
                    usuarios=new ArrayList<>();
                    for(Usuario u: response.body()){
                        usuarios.add(u);
                    }
                    todosMLD.postValue(usuarios);
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });
    }

    public void cantidadActividades(){
        Call<List<Actividad>> dato=ApiClient.getMyApiClient().getActividades(token);
        dato.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                if(!response.body().isEmpty()&&getActividadesMLD()!=null){
                    actividades=new ArrayList<>();
                    for(Actividad a: response.body()){
                        actividades.add(a);
                        actividadesMLD.postValue(actividades);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });
    }

    public void cantidadEventos(){
        Call<List<Evento>> dato= ApiClient.getMyApiClient().getEventos(token);
        dato.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if(!response.body().isEmpty()&&getEventoMLD()!=null){
                    eventos=new ArrayList<>();
                    for(Evento e: response.body()){
                        eventos.add(e);
                        eventosMLD.postValue(eventos);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });
    }

}
