package com.example.finalhealty.administrador.crearYlistarUsuario;

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

public class CrearUsuario extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewModelProviders.of(this).get(UsuarioMainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_crear_usuario, container, false);
        ((AdminMain) getActivity()).setActionBarTitle("ADMINISTRADOR - Crear Usuario");

        return root;
    }
}
