package com.example.finalhealty.ui.eventos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.model.Evento;
import com.example.finalhealty.model.InscripcionAEvento;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.request.ApiClient;
import com.example.finalhealty.ui.home.HomeViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventosViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Usuario> usuarioMutableLiveData;
    private MutableLiveData<List<Evento>> misEventosMutableLiveData;
    public static List<InscripcionAEvento> inscripcionesAEventos;
    public static List<Evento> misEventos;
    public static List<Evento> eventosDisponibles;
    private SharedPreferences sp;

    public EventosViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }
/*
    public LiveData<Usuario> getPropietarioMutableLiveData(){
        if(usuarioMutableLiveData==null){
            usuarioMutableLiveData=new MutableLiveData<>();

            //if(inscripcionesAEventos==null){inscripcionesAEventos= HomeViewModel.inscripcionesAEventos;}

        misEventos=HomeViewModel.misEventos;
        eventosDisponibles=HomeViewModel.eventosDisponibles;
        }


        return usuarioMutableLiveData;
    }

//TRUE METHODS:
    public void obtenerMisEventos() {
        Call<List<Evento>> dato = ApiClient.getMyApiClient().getMisEventos(sp.getString("token",""));
        dato.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if (response.isSuccessful()) {
                    misEventosMutableLiveData.postValue(response.body());
                } else {

                    Toast.makeText(getApplication(),"error al cargar eventos", Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Toast.makeText(getApplication(),t.getMessage(), Toast.LENGTH_LONG);
            }
        });
    }

*/
}