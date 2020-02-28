package com.example.finalhealty.coordinador.ui.coordmain.eventos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.finalhealty.R;
import com.example.finalhealty.model.Evento;

public class CrearEvento extends Fragment {
    private CordEventoViewModel cordEventoViewModel;
    private View v;
    private EditText nombre, descripcion, horario;
    private Button boton;
    private Spinner spinnerActividades;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        cordEventoViewModel =
                ViewModelProviders.of(this).get(CordEventoViewModel.class);
        v = inflater.inflate(R.layout.crear_evento, container, false);
        spinnerActividades= v.findViewById(R.id.spinnerActivCrearEvento);
        nombre=v.findViewById(R.id.etTituloEvento);
        descripcion=v.findViewById(R.id.etDescripEvento);
        horario=v.findViewById(R.id.etHorarioEvento);
        boton=v.findViewById(R.id.crearEvento);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crear();
            }
        });




//No se como llenarlo con objetos.......
 //spinnerActividades.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.crear_evento,listaTituloActividades));



        return v;
    }

    public void crear(){
        Evento e=new Evento();
        e.setTitulo(nombre.getText().toString());
        e.setDescripcion(descripcion.getText().toString());
        e.setFechaHora(horario.getText().toString());

        cordEventoViewModel.crearEvento(e);
        cordEventoViewModel.obtenerMisEventos();

        horario.setText("");
        nombre.setText("");
        descripcion.setText("");
    }
}
