package com.example.finalhealty.coordinador.ui.coordmain.eventos;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.finalhealty.R;
import com.example.finalhealty.coordinador.CoordMain;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.ui.ViewPagerAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class cord_eventos extends Fragment {
    private CordEventoViewModel cordEventoViewModel;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private AppBarLayout appBar;
    private ViewPagerAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        cordEventoViewModel=ViewModelProviders.of(this).get(CordEventoViewModel.class);

        View root = inflater.inflate(R.layout.fragment_cord_eventos, container, false);
        ((CoordMain) getActivity()).setActionBarTitle("Coordinadores - Eventos");


        viewPager=root.findViewById(R.id.viewPagerCo);
        appBar=root.findViewById(R.id.appBarCo);
        tabLayout=new TabLayout(getContext());

        appBar.addView(tabLayout);

        adapter=new ViewPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new CrearEvento(),"NUEVO EVENTO");
        adapter.addFragment(new FragMisEventos(),"MIS EVENTOS");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return root;
    }
}
