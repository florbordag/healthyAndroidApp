package com.example.finalhealty.coordinador.ui.coordmain.Logout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalhealty.R;
import com.example.finalhealty.administrador.AdminMain;
import com.example.finalhealty.administrador.Logout.LogoutAdminViewModel;
import com.example.finalhealty.coordinador.CoordMain;
import com.example.finalhealty.ui.inicio.MainActivity;


public class LogoutCoord extends Fragment {
    private LogoutCoordViewModel logoutCoordViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logoutCoordViewModel=ViewModelProviders.of(this).get(LogoutCoordViewModel.class);
        View root = inflater.inflate(R.layout.fragment_logout_coord, container, false);
        ((CoordMain) getActivity()).setActionBarTitle("COORDINADOR - Salir");

        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new AlertDialog.Builder(getContext())
                .setTitle("Cerrar sesion")
                .setMessage("¿Desea abandonar de la aplicacion?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logoutCoordViewModel.logOut();
                        Intent i = new Intent(getContext(), MainActivity.class);
                        startActivity(i);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent i = new Intent(getContext(), CoordMain.class);
                startActivity(i);
            }
        }).show();
    }
}
