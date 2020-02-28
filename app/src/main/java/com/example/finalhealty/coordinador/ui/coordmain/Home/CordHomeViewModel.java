package com.example.finalhealty.coordinador.ui.coordmain.Home;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.ShowToast;
import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.model.Evento;
import com.example.finalhealty.model.InscripcionEvento;
import com.example.finalhealty.model.Participante;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.request.ApiClient;
import com.example.finalhealty.ui.inicio.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CordHomeViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Usuario> usuarioMutableLiveData;
    private MutableLiveData<Integer> todosMLD;
    private MutableLiveData<List<Evento>> eventosMLD;
    private MutableLiveData<List<Actividad>> actividadesMLD;
    private SharedPreferences sp;
    private Usuario user= MainActivity.usuarioReal;
    private String token;

    public CordHomeViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();

        sp= context.getSharedPreferences("token",0);
        token=sp.getString("token","");
    }

    public LiveData<Usuario> getPropietarioMutableLiveData(){
        if(usuarioMutableLiveData==null){
            usuarioMutableLiveData=new MutableLiveData<>();
        }
        return usuarioMutableLiveData;
    }

    public LiveData<Integer> getTodosMLD() {
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

    public void putPassword(String pass1,String pass2){
        Call<Usuario> dato = ApiClient.getMyApiClient().putClaveUsuario(
                token,
                user.getId(),
                pass1, pass2 );

        dato.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    usuarioMutableLiveData.postValue(response.body());
                    new ShowToast(context, "Actualización exitosa");
                } else {
                    new ShowToast(context, response.message()+ ". Problema en putPassword");
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

                new ShowToast(context, t.getMessage());
            }
        });
    }

    public void obtenerUsuarios(){
        Call<Integer> dato= ApiClient.getMyApiClient().total(token);
        dato.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()&&getTodosMLD()!=null){
                    todosMLD.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
            }
        });
    }

    public void obtenerActividades(){
        Call<List<Actividad>> dato= ApiClient.getMyApiClient().getTodasCoord(token);
        dato.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                if(response.isSuccessful()&&getActividadesMLD()!=null){
                    actividadesMLD.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
            }
        });
    }

    public void obtenerEventos(){
        Call<List<Evento>> dato=ApiClient.getMyApiClient().getTodosEventosCoord(token);
        dato.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if(response.isSuccessful()&&getEventoMLD()!=null){
                    eventosMLD.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {

            }
        });
    }
}
