package com.example.finalhealty.administrador.Home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalhealty.R;
import com.example.finalhealty.administrador.AdminMain;
import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.model.Evento;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.request.ApiClient;
import com.example.finalhealty.ui.inicio.MainActivity;

import java.util.List;


public class Home_admin extends Fragment {
    private HomeAdminViewModel homeAdminViewModel;
    private TextView todes, mies,actividades,eventes;
    private int contador, total, sumando, noRestando;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeAdminViewModel=ViewModelProviders.of(this).get(HomeAdminViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home_admin, container, false);
        ((AdminMain) getActivity()).setActionBarTitle("ADMIN - Home");

        todes=root.findViewById(R.id.tvCantUser);
        mies=root.findViewById(R.id.tvCantUserMios);
        actividades=root.findViewById(R.id.tvTotalActividades);
        eventes=root.findViewById(R.id.tvTotalEventos);

        contador=0;total=0;sumando=0;noRestando=0;

        getUsuarios();
        homeAdminViewModel.cantidadUsuarios();
        getActividades();
        homeAdminViewModel.cantidadActividades();
        getEventos();
        homeAdminViewModel.cantidadEventos();

        return root;
    }

    public void getUsuarios(){
        homeAdminViewModel.getTodosMLD().observe(this, new Observer<List<Usuario>>() {
            @Override
            public void onChanged(List<Usuario> usuarios) {
                    for(Usuario u: usuarios){
                        todes.setText(""+total++);
                        if(u.getAdminUltModId()== MainActivity.usuarioReal.getId()){
                            mies.setText(""+contador++);
                        }
                }
            }
        });
    }

    public void getActividades(){
        homeAdminViewModel.getActividadesMLD().observe(this, new Observer<List<Actividad>>() {
            @Override
            public void onChanged(List<Actividad> actividads) {
                    for(Actividad u: actividads){
                        actividades.setText(""+sumando++);
                    }

            }
        });
    }

    public void getEventos(){
        homeAdminViewModel.getEventoMLD().observe(this, new Observer<List<Evento>>() {
            @Override
            public void onChanged(List<Evento> eventos) {
                for(Evento e: eventos){
                    eventes.setText(""+noRestando++);
                }
            }
        });
    }





}