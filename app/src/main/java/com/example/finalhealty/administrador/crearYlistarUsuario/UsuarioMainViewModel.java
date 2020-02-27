package com.example.finalhealty.administrador.crearYlistarUsuario;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.model.Usuario;

import java.util.List;

public class UsuarioMainViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Usuario>> usuarioMutableLiveData;

    public UsuarioMainViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }


    public LiveData<List<Usuario>> getUsuariosMLD(){
        if(usuarioMutableLiveData==null){
            usuarioMutableLiveData=new MutableLiveData<>();
        }
        return usuarioMutableLiveData;
    }
}
