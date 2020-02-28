package com.example.finalhealty.ui.logout;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.ui.inicio.MainActivity;

public class LogoutViewModel extends AndroidViewModel {
    private Context context;
    private SharedPreferences sp;

    public LogoutViewModel(@NonNull Application application) {
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