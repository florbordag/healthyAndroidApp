package com.example.finalhealty.administrador.ListarEventos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.model.Usuario;

public class ListarEventosViewModel  extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> usuarioMutableLiveData;

    public ListarEventosViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<Usuario> getPropietarioMutableLiveData(){
        if(usuarioMutableLiveData==null){
            usuarioMutableLiveData=new MutableLiveData<>();
        }
        return usuarioMutableLiveData;
    }
}