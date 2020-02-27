package com.example.finalhealty.coordinador.ui.coordmain.Logout;

import android.content.Context;
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


public class LogoutCoord extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewModelProviders.of(this).get(LogoutCoordViewModel.class);
        View root = inflater.inflate(R.layout.fragment_logout_coord, container, false);
        ((CoordMain) getActivity()).setActionBarTitle("COORDINADOR - Salir");

        return root;
    }
}
