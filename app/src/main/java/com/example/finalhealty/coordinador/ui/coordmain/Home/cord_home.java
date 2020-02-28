package com.example.finalhealty.coordinador.ui.coordmain.Home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.finalhealty.R;
import com.example.finalhealty.ShowToast;
import com.example.finalhealty.coordinador.CoordMain;
import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.model.Evento;
import com.example.finalhealty.model.InscripcionEvento;
import com.example.finalhealty.model.Participante;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.ui.inicio.MainActivity;
import com.example.finalhealty.ui.perfil.PerfilFragment;
import com.example.finalhealty.ui.perfil.PerfilViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class cord_home extends Fragment {
    private CordHomeViewModel cordHomeViewModel;
    private TextView userTotales, actiMias, actiTotales, eventMios, evenTotales, passCord;
    private FloatingActionButton editarPass;
    private String pass1,pass2,pass3;
    private int totales,mias,totalas,mios,totulus;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        cordHomeViewModel= ViewModelProviders.of(this).get(CordHomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cord_home, container, false);
        ((CoordMain) getActivity()).setActionBarTitle("Coordinadores - Home");

        inicializarElementos(root);
        
        totales=0;mias=0;totalas=0;mios=0;totulus=0;
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

        getUsuarios();
        cordHomeViewModel.obtenerUsuarios();
        getActividades();
        cordHomeViewModel.obtenerActividades();
        getEvento();
        cordHomeViewModel.obtenerEventos();

        return root;
    }

    public void inicializarElementos(View root){
        userTotales= root.findViewById(R.id.tvCantUserCord);
        actiMias=root.findViewById(R.id.tvActiPropiasCord);
        actiTotales= root.findViewById(R.id.tvTotalActiviCord);
        eventMios= root.findViewById(R.id.tvEventPropios);
        evenTotales= root.findViewById(R.id.tvTotalEvenCord);
        passCord= root.findViewById(R.id.tvPassCoord);
        editarPass= root.findViewById(R.id.editarPassCoord);
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
                    if(pass2!=pass3){
                        new ShowToast(getContext(),"Las contraseñas deben coincidir");
                    } else {
                        cordHomeViewModel.putPassword(pass1,pass2);
                    }
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

    public void getUsuarios(){
        cordHomeViewModel.getTodosMLD().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                userTotales.setText(integer+"");
            }
        });
    }

    public void getActividades(){
        cordHomeViewModel.getActividadesMLD().observe(this, new Observer<List<Actividad>>() {
            @Override
            public void onChanged(List<Actividad> actividads) {
                for(Actividad a: actividads){
                    if(a.getCoordinadorId()== MainActivity.usuarioReal.getId()){
                        mias++;
                        actiMias.setText(mias+"");
                    }
                    totalas++;
                    actiTotales.setText(totalas+"");
                }
            }
        });

    }

    public void getEvento(){
        cordHomeViewModel.getEventoMLD().observe(this, new Observer<List<Evento>>() {
            @Override
            public void onChanged(List<Evento> eventos) {
                for(Evento e:eventos){
                    if(e.getActividad().getCoordinadorId()==MainActivity.usuarioReal.getId()){
                        mios++;
                        eventMios.setText(mios+"");
                    }
                    totulus++;
                    evenTotales.setText(totulus+"");
                }
            }
        });
    }

}
