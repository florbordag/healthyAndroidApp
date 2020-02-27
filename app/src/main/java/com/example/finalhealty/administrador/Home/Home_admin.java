package com.example.finalhealty.administrador.Home;

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
import com.example.finalhealty.model.Usuario;


public class Home_admin extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewModelProviders.of(this).get(HomeAdminViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home_admin, container, false);
        ((AdminMain) getActivity()).setActionBarTitle("ADMINISTRADOR - Home");

        return root;
    }
}