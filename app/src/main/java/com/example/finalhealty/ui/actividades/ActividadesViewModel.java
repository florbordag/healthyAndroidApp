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
import com.example.finalhealty.ui.inicio.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadesViewModel extends AndroidViewModel {
    private Context context;
    private List<Participante> participaciones;
    private List<Actividad> misActividades;

    private List<Actividad> dispoActividades;
    private MutableLiveData<Usuario> usuarioMutableLiveData;
    private MutableLiveData<List<Actividad>> actividadesMutableLiveData;
    private SharedPreferences sp;

    public ActividadesViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<Usuario> getUserMutableLiveData() {
        if (usuarioMutableLiveData == null) {
            usuarioMutableLiveData = new MutableLiveData<>();

            if(participaciones==null){participaciones=HomeViewModel.participaciones;}

            misActividades= HomeViewModel.misActividades;
            dispoActividades=HomeViewModel.dispoActividades;

        }
        return usuarioMutableLiveData;
    }


    public List<Participante> obtenerParticipantes() {
        //FALSE METHOD

        return participaciones;


        //TRUE METHOD : obtener participantes donde idParticipante == user.id Y estado==1

        /*String parametro="[{\"id\":\""+"\" ,\"password\":\""+"\"}]";
        Call<List<Participante>> dato= ApiClient.getMyApiClient().listarParticipacion(parametro);
        dato.enqueue(new Callback<List<Participante>>() {
            @Override
            public void onResponse(Call<List<Participante>> call, Response<List<Participante>> response) {
                if(response.isSuccessful()){

                    misActividadesMLD.postValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Participante>> call, Throwable t) {
                Log.d("salida huesped",t.getMessage());
            }
        });*/


    }

    public List<Actividad> obtenerMisActividades() {
        int x = 0;
        List<Actividad> esta = new ArrayList<>();

        if(participaciones!=null) {

            while (x < participaciones.size()) {

                if(participaciones.get(x).getEstadoParticipante()==1)
                {  esta.add(participaciones.get(x).getActividad());}


                x += 1;
            }

        } return esta;
    }

    public List<Actividad> obtenerDisponibles() {
        Call<List<Actividad>> dato = ApiClient.getMyApiClient().getActividadesDisponibles(
                sp.getString("token", ""));
        dato.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                if (response.isSuccessful()) {
                    actividadesMutableLiveData.postValue(response.body());
                    dispoActividades=response.body();
                } else {
                    new ShowToast(context, response.message() + ". Problema al obtener actividades disponibles");
                }
            }

            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });
        return dispoActividades;
    }


    //TRUE METHODS
    public void trueMisActividades() {
        Call<List<Actividad>> dato = ApiClient.getMyApiClient().getMisActividades(sp.getString("token",""));
        dato.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                if (response.isSuccessful()) {
                   actividadesMutableLiveData.postValue(response.body());
                } else {
                    Toast.makeText(getApplication(),"error al cargar Actividades", Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
                Toast.makeText(getApplication(),t.getMessage(), Toast.LENGTH_LONG);
            }
        });
    }

}

