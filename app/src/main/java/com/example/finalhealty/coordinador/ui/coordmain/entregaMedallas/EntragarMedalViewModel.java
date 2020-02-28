package com.example.finalhealty.coordinador.ui.coordmain.entregaMedallas;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.ShowToast;
import com.example.finalhealty.model.MedallaVirtual;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntragarMedalViewModel extends AndroidViewModel {
    private Context context;
    private String token;
    private SharedPreferences sp;
    private Usuario u;
    private MedallaVirtual m;

    public EntragarMedalViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        sp=context.getSharedPreferences("token",0);
        token=sp.getString("token","");
    }


    public void entregarMedalla(final MedallaVirtual medallaVirtual){
        Call<MedallaVirtual> dato=ApiClient.getMyApiClient().crearMedalla(token,medallaVirtual);
        dato.enqueue(new Callback<MedallaVirtual>() {
            @Override
            public void onResponse(Call<MedallaVirtual> call, Response<MedallaVirtual> response) {
                if(response.isSuccessful()){
                    m=new MedallaVirtual();
                    m=response.body();
                    new ShowToast(context,m.getUsuario().getNombre()+" "+
                            m.getUsuario().getApellido()+" ha recibido una medalla de "+
                            m.getColor());
                }
            }

            @Override
            public void onFailure(Call<MedallaVirtual> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });
    }

    public Usuario obtenerPorMail(String mail){
        Call<Usuario> dato= ApiClient.getMyApiClient().validar(token,mail);
        dato.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    u= new Usuario();
                    u=response.body();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                new ShowToast(context,t.getMessage()+": "+t.getStackTrace().toString());
            }
        });
        return u;
    }


}
