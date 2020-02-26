package com.example.finalhealty.coordinador.ui.coordmain.actividades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.finalhealty.R;
import com.example.finalhealty.ui.ViewPagerAdapter;
import com.example.finalhealty.coordinador.CoordMain;
import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.model.Usuario;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class CordActividades extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private CordActiViewModel cordActiViewModel;
    static List<Actividad> misActivCord;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cordActiViewModel =
                ViewModelProviders.of(this).get(CordActiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_coord_actividades, container, false);

        ((CoordMain) getActivity()).setActionBarTitle("Coordinadores - Actividades");


        tabLayout= (TabLayout) root.findViewById(R.id.tabLayCoorActivi);
        viewPager = (ViewPager) root.findViewById(R.id.vp_coorActivi);
        adapter = new ViewPagerAdapter(getChildFragmentManager());




cordActiViewModel.getMisActividadesMLD().observe(this, new Observer<List<Actividad>>() {
            @Override
            public void onChanged(List<Actividad> actividads) {
                misActivCord=actividads;
            }
        }
);


        cordActiViewModel.getPropietarioMutableLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable Usuario usuario) {
                //propietarioVisto=usuario;

                //fijarDatos(usuario);
            }
        });



        adapter.addFragment(new FragCrearActividad(),"NUEVA ACTIVIDAD");
        adapter.addFragment(new FragMisActividades(),"MIS ACTIVIDADES ");
        adapter.addFragment(new FragActivDisponibles(),"ACTIVIDES DISPONIBLES");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return root;
    }
}