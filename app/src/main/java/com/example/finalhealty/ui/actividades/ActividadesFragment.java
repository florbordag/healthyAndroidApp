package com.example.finalhealty.ui.actividades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.finalhealty.R;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.ui.ViewPagerAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;


public class ActividadesFragment extends Fragment {

    private ActividadesViewModel actividadesViewModel;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private AppBarLayout appBar;
    private ViewPagerAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        actividadesViewModel =
                ViewModelProviders.of(this).get(ActividadesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_actividades, container, false);


        viewPager=root.findViewById(R.id.viewPager);
        appBar=root.findViewById(R.id.appBar);
        tabLayout=new TabLayout(getContext());

        appBar.addView(tabLayout);

        adapter=new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentMisActividades(),"MIAS");
        adapter.addFragment(new FragmentActividadesDisponibles(), "DISPONIBLES");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return root;
    }

}