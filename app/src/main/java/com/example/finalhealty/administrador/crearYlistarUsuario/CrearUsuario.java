package com.example.finalhealty.administrador.crearYlistarUsuario;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finalhealty.R;
import com.example.finalhealty.administrador.AdminMain;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.ui.inicio.MainActivity;

public class CrearUsuario extends Fragment {
    private UsuarioMainViewModel usuarioMainViewModel;
    private String rolSeleccionado;
    private Spinner spinnerRol;
    private EditText mail, nombre, apellido, dni, pass, fecNac;
    private Button boton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        usuarioMainViewModel=ViewModelProviders.of(this).get(UsuarioMainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_crear_usuario, container, false);
        ((AdminMain) getActivity()).setActionBarTitle("ADMIN - Crear Usuario");

        spinnerRol= root.findViewById(R.id.spinnerRol);
        mail= root.findViewById(R.id.etCrearMail);
        nombre= root.findViewById(R.id.etCrearNombre);
        apellido= root.findViewById(R.id.etCrearApellido);
        dni= root.findViewById(R.id.etCrearApellido);
        pass= root.findViewById(R.id.etCrearPassword);
        fecNac= root.findViewById(R.id.etCrearFecnac);

        boton=root.findViewById(R.id.guardarUsuario);

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

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Crear usuario")
                        .setMessage("¿Desea insertar el usuario "+nombre.getText()+" "+apellido.getText().toString()+" en la base de datos?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                crear();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });

        return root;
    }

    public void crear(){
        Usuario u = new Usuario();
        u.setEmpresa(MainActivity.usuarioReal.getEmpresa());
        u.setMail(mail.getText().toString());
        u.setNombre(nombre.getText().toString());
        u.setApellido(apellido.getText().toString());
        u.setDni(dni.getText().toString());
        u.setPassword(pass.getText().toString());
        u.setRol(rolSeleccionado);
        u.setFecNac(fecNac.getText().toString());
        u.setFumador(0);

        usuarioMainViewModel.crearUSuario(u);
        //obtener

        mail.setText("@healthy.com");
        nombre.setText("");
        apellido.setText("");
        dni.setText("");
        fecNac.setText("");
    }
}
