package com.example.finalhealty.coordinador.ui.coordmain.actividades;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class CordActiViewModel extends AndroidViewModel {
    private Context context;
    public static List<Actividad> mientrasTanto = new ArrayList<>();
    private MutableLiveData<Usuario> usuarioMutableLiveData;
    private MutableLiveData<List<Actividad>> misActividadesLD;

    public CordActiViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<Usuario> getPropietarioMutableLiveData(){
        if(usuarioMutableLiveData==null){
            usuarioMutableLiveData=new MutableLiveData<>();
        }
        return usuarioMutableLiveData;
    }

    public LiveData<List<Actividad>> getMisActividadesMLD(){
        if(misActividadesLD==null){ misActividadesLD=new MutableLiveData<>();}
        return misActividadesLD;
    }
}
