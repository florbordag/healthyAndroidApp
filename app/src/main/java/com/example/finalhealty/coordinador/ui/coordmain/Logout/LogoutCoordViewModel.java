package com.example.finalhealty.coordinador.ui.coordmain.Logout;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.model.Usuario;

public class LogoutCoordViewModel extends AndroidViewModel {
    private Context context;
    private SharedPreferences sp;

    public LogoutCoordViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        sp=context.getSharedPreferences("token",0);
    }

    public void logOut(){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token" , "");
        editor.commit();
    }

}