package com.example.finalhealty.coordinador.ui.coordmain.Home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.finalhealty.R;
import com.example.finalhealty.coordinador.CoordMain;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.ui.perfil.PerfilFragment;
import com.example.finalhealty.ui.perfil.PerfilViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class cord_home extends Fragment {
    private CordHomeViewModel cordHomeViewModel;
    private TextView userTotales, actiMias, actiTotales, eventMios, evenTotales, inscriptosMisEvent;
    private TextView passCord;
    private FloatingActionButton editarPass;
    private String pass1,pass2,pass3;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cordHomeViewModel= ViewModelProviders.of(this).get(CordHomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cord_home, container, false);
        ((CoordMain) getActivity()).setActionBarTitle("Coordinadores - Home");

        userTotales= root.findViewById(R.id.tvCantUserCord);
        actiMias=root.findViewById(R.id.tvActiPropiasCord);
        actiTotales= root.findViewById(R.id.tvTotalActiviCord);
        eventMios= root.findViewById(R.id.tvEventPropios);
        evenTotales= root.findViewById(R.id.tvTotalEvenCord);

        passCord= root.findViewById(R.id.tvPassCoord);
        editarPass= root.findViewById(R.id.editarPassCoord);



        editarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CambiarPassDialog();
            }
        });



  /*      cordHomeViewModel.getPropietarioMutableLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable Usuario usuario) {
                propietarioVisto=usuario;


            }
        }); */
        return root;
    }


    public void CambiarPassDialog() {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View textenter = inflater.inflate(R.layout.dialog_add, null);
        final EditText userinput1 = textenter.findViewById(R.id.item_added1);
        final EditText userinput2 = textenter.findViewById(R.id.item_added2);
        final EditText userinput3 = textenter.findViewById(R.id.item_added3);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(textenter)
                .setTitle("Ingrese su nueva clave");
        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {
                pass1 =  userinput1.getText().toString();
                pass2 =  userinput2.getText().toString();
                pass3=  userinput3.getText().toString();
                if(!pass1.equals("")&&!pass2.equals("")&&!pass3.equals("")){

                    //EDITAR ESTA PARTE
                    Toast.makeText(getActivity(), "EDITAR ESTA ACCION", Toast.LENGTH_LONG).show();
                    //perfilViewModel.putPassword(pass1,pass2,pass3);
                }
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
