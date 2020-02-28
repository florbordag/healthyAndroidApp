package com.example.finalhealty.coordinador.ui.coordmain.entregaMedallas;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class EntragarMedalViewModel extends AndroidViewModel {
    private Context context;
    private SharedPreferences sp;

    public EntragarMedalViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        sp=context.getSharedPreferences("token",0);
    }





}
