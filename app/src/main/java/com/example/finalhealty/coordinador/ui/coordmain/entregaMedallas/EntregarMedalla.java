package com.example.finalhealty.coordinador.ui.coordmain.entregaMedallas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finalhealty.R;
import com.example.finalhealty.ShowToast;
import com.example.finalhealty.coordinador.CoordMain;
import com.example.finalhealty.model.MedallaVirtual;
import com.example.finalhealty.model.Usuario;


public class EntregarMedalla extends Fragment {
    private EntragarMedalViewModel entragarMedalViewModel;
    private Spinner spinnerColorMedal;
    private String colorSeleccionado;
    private EditText titulo, descripcion, mail;
    private Button boton;
    private Usuario u;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        entragarMedalViewModel=
                ViewModelProviders.of(this).get(EntragarMedalViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_entregar_medalla, container, false);
        ((CoordMain) getActivity()).setActionBarTitle("Coordinadores - Entregar Medalla");

        spinnerColorMedal= root.findViewById(R.id.spinnerColorMedal);
        titulo = root.findViewById(R.id.etTituloMedalla);
        descripcion= root.findViewById(R.id.etDescripMedalla);
        boton=root.findViewById(R.id.botonMedalla);
        mail=root.findViewById(R.id.etMail);


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


        //Obtener el usuario del mail

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mail.getText()!=null&&titulo.getText()!=null&&descripcion.getText()!=null){
                    new AlertDialog.Builder(getContext())
                            .setTitle("Otorgar medalla")
                            .setMessage("¿Desea entregar esta honorable medalla de "+colorSeleccionado+"?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    u=entragarMedalViewModel.obtenerPorMail(mail.getText().toString());
                                    if(u!=null){
                                        crearMedalla();
                                    }else{
                                        new ShowToast(getContext(),"El mail ingresado no corresponde a ningún usuario registrado");
                                    }
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();

                }
            }
        });

        return root;
    }


    public void crearMedalla(){
        MedallaVirtual medalla = new MedallaVirtual();
        medalla.setNombre(titulo.getText().toString());
        medalla.setDescripcion(descripcion.getText().toString());
        medalla.setColor(colorSeleccionado);
        medalla.setUsuario(u);

        entragarMedalViewModel.entregarMedalla(medalla);

    }
}
