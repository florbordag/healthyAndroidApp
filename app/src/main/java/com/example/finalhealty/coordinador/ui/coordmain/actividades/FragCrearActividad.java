package com.example.finalhealty.coordinador.ui.coordmain.actividades;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.finalhealty.R;
import com.example.finalhealty.model.Actividad;

public class FragCrearActividad extends Fragment {
    private CordActiViewModel cordActiViewModel;
    private View v;
    private EditText nombre, descripcion, horario;
    private Button boton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        cordActiViewModel =
                ViewModelProviders.of(this).get(CordActiViewModel.class);
        v = inflater.inflate(R.layout.crear_actividad, container, false);

        nombre=v.findViewById(R.id.etNombreActivi);
        descripcion=v.findViewById(R.id.etDescripActivi);
        horario=v.findViewById(R.id.etHorarioActivi);
        boton=v.findViewById(R.id.btnCrearActi);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crear();
            }
        });

        return v;
    }

    public void crear(){
        Log.d("datos ",nombre.getText().toString()+" "+descripcion.getText().toString()+" "+horario.getText().toString());
        Actividad a = new Actividad();
        a.setTitulo(nombre.getText().toString());
        a.setDescripcion(descripcion.getText().toString());
        a.setHorario(horario.getText().toString());

        cordActiViewModel.crearActividad(a);
        cordActiViewModel.obtenerMisActividades();

        horario.setText("");
        nombre.setText("");
        descripcion.setText("");
    }
}
