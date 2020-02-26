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

    private List<Actividad> misActividades=new ArrayList<>();
    private List<Actividad> dispoActividades=new ArrayList<>();

    private MutableLiveData<List<Actividad>> miActividadMutableLiveData;
    private MutableLiveData<List<Actividad>> suActividadMutableLiveData;

    private SharedPreferences sp;

    public ActividadesViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();
        sp=context.getSharedPreferences("token",0);

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

    public void obtenerActividadesDisponibles() {
        Call<List<Actividad>> dato= ApiClient.getMyApiClient().getActividadesDisponibles(sp.getString("token",""));
        dato.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                if(!response.body().isEmpty()){
                    dispoActividades=new ArrayList<>();
                    for (Actividad a: response.body()) {

                        dispoActividades.add(a);
                    }
                    miActividadMutableLiveData.postValue(dispoActividades);
                }
            }

            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }


}

