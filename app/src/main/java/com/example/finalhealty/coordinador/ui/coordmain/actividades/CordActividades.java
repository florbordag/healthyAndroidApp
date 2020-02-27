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
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class CordActividades extends Fragment {
    private CordActiViewModel cordActiViewModel;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private AppBarLayout appBar;
    private ViewPagerAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cordActiViewModel =
                ViewModelProviders.of(this).get(CordActiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_coord_actividades, container, false);

        ((CoordMain) getActivity()).setActionBarTitle("Coordinadores - Actividades");


        viewPager=root.findViewById(R.id.viewPagerC);
        appBar=root.findViewById(R.id.appBarC);
        tabLayout=new TabLayout(getContext());

        appBar.addView(tabLayout);

        adapter=new ViewPagerAdapter(getChildFragmentManager());


        adapter.addFragment(new FragCrearActividad(),"NUEVA ACTIVIDAD");
        adapter.addFragment(new FragMisActividades(),"MIS ACTIVIDADES ");
        adapter.addFragment(new FragActivDisponibles(),"ACTIVIDES DISPONIBLES");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return root;
    }
}