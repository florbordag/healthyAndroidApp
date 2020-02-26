package com.example.finalhealty.ui.eventos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.finalhealty.R;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.ui.ViewPagerAdapter;
import com.example.finalhealty.ui.actividades.FragmentActividadesDisponibles;
import com.example.finalhealty.ui.actividades.FragmentMisActividades;
import com.google.android.material.tabs.TabLayout;


public class EventosFragment extends Fragment {

    private EventosViewModel eventosViewModel;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventosViewModel =
                ViewModelProviders.of(this).get(EventosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_eventos, container, false);

        tabLayout= (TabLayout) root.findViewById(R.id.tabLayoutEventos);
        viewPager = (ViewPager) root.findViewById(R.id.view_pagerEventos);
        adapter = new ViewPagerAdapter(getChildFragmentManager());

/*
        eventosViewModel.getPropietarioMutableLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable Usuario usuario) {
                //propietarioVisto=usuario;

                //fijarDatos(usuario);
            }
        });*/

        adapter.addFragment(new FragmentMisEventos(),"MIS EVENTOS");
        adapter.addFragment(new FragmentEventosDisponibles(), "DISPONIBLES");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return root;
    }


}

