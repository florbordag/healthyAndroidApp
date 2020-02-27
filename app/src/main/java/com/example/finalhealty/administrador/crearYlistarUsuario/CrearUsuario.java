package com.example.finalhealty.administrador.crearYlistarUsuario;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finalhealty.R;
import com.example.finalhealty.administrador.AdminMain;

public class CrearUsuario extends Fragment {
private String rolSeleccionado;
private Spinner spinnerRol;
private EditText mail, nombre, apellido, dni, pass, fecNac;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewModelProviders.of(this).get(UsuarioMainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_crear_usuario, container, false);
        ((AdminMain) getActivity()).setActionBarTitle("ADMINISTRADOR - Crear Usuario");
        spinnerRol= root.findViewById(R.id.spinnerRol);
        mail= root.findViewById(R.id.etCrearMail);
        nombre= root.findViewById(R.id.etCrearNombre);
        apellido= root.findViewById(R.id.etCrearApellido);
        dni= root.findViewById(R.id.etCrearApellido);
        pass= root.findViewById(R.id.etCrearPassword);
        fecNac= root.findViewById(R.id.etCrearFecnac);



        spinnerRol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {

                rolSeleccionado= (String) adapterView.getItemAtPosition(pos);
                Toast.makeText(adapterView.getContext(),rolSeleccionado, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });





        return root;
    }
}
