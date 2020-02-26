package com.example.finalhealty.coordinador.ui.coordmain.eventos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.model.Usuario;

public class CordEventoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> misEventosMutableLiveData;

    public CordEventoViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<Usuario> getMisEventos(){
        if(misEventosMutableLiveData==null){
            misEventosMutableLiveData=new MutableLiveData<>();
        }
        return misEventosMutableLiveData;
    }
}
