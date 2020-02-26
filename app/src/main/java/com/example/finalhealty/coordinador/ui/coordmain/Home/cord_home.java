package com.example.finalhealty.coordinador.ui.coordmain.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.finalhealty.R;
import com.example.finalhealty.coordinador.CoordMain;
import com.example.finalhealty.model.Usuario;


public class cord_home extends Fragment {
    CordHomeViewModel cordHomeViewModel;
    Usuario propietarioVisto;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
                ViewModelProviders.of(this).get(CordHomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cord_home, container, false);
        ((CoordMain) getActivity()).setActionBarTitle("Coordinadores - Home");
  /*      cordHomeViewModel.getPropietarioMutableLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable Usuario usuario) {
                propietarioVisto=usuario;


            }
        }); */
        return root;
    }
}
