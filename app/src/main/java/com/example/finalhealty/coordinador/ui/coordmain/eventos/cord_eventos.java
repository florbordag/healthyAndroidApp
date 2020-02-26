package com.example.finalhealty.coordinador.ui.coordmain.eventos;


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

public class cord_eventos extends Fragment {
    CordEventoViewModel cordEventoViewModel;
    Usuario propietarioVisto;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewModelProviders.of(this).get(CordEventoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cord_eventos, container, false);
        final TextView textView = root.findViewById(R.id.homehome);
        ((CoordMain) getActivity()).setActionBarTitle("Coordinadores - Eventos");
  /*      cordHomeViewModel.getPropietarioMutableLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable Usuario usuario) {
                propietarioVisto=usuario;


            }
        }); */
        return root;
    }
}
