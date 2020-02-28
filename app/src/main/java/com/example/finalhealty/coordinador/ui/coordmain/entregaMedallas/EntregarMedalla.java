package com.example.finalhealty.coordinador.ui.coordmain.entregaMedallas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finalhealty.R;
import com.example.finalhealty.coordinador.CoordMain;
import com.example.finalhealty.model.MedallaVirtual;


public class EntregarMedalla extends Fragment {
    private EntragarMedalViewModel entragarMedalViewModel;
    private Spinner spinnerColorMedal;
    private String colorSeleccionado;
    private EditText titulo, descripcion;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        entragarMedalViewModel=
                ViewModelProviders.of(this).get(EntragarMedalViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_entregar_medalla, container, false);
        ((CoordMain) getActivity()).setActionBarTitle("Coordinadores - Entregar Medalla");

        spinnerColorMedal= root.findViewById(R.id.spinnerColorMedal);
        titulo = root.findViewById(R.id.etTituloMedalla);
        descripcion= root.findViewById(R.id.etDescripMedalla);




        spinnerColorMedal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {

                colorSeleccionado= (String) adapterView.getItemAtPosition(pos);

                //Aqui la accion
                Toast.makeText(adapterView.getContext(),colorSeleccionado, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });


      //  cargarActividades(root);
       // cargarEventos(root);

        //homeViewModel.obtenerMisActividades();
        //homeViewModel.obtenerMisEventos();

        return root;
    }


    public void crearMedalla(){
        MedallaVirtual medalla = new MedallaVirtual();
        medalla.setNombre(titulo.getText().toString());
        medalla.setDescripcion(descripcion.getText().toString());
        medalla.setColor(colorSeleccionado);
        //completar con cosas del usuario

        //entragarMedalViewModel.entregarMedalla();

    }
}
