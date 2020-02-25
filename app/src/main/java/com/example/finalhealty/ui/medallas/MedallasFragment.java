package com.example.finalhealty.ui.medallas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.finalhealty.R;
import com.example.finalhealty.model.Usuario;

public class MedallasFragment extends Fragment {

    private MedallasViewModel medallasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        medallasViewModel =
                ViewModelProviders.of(this).get(MedallasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_medallas, container, false);
        final TextView textView = root.findViewById(R.id.text_share);
        medallasViewModel.getPropietarioMutableLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable Usuario usuario) {
                //propietarioVisto=usuario;

                //fijarDatos(usuario);
            }
        });
        return root;
    }
}