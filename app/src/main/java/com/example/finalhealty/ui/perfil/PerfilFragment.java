package com.example.finalhealty.ui.perfil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.finalhealty.R;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.request.ApiClient;
import com.example.finalhealty.ui.home.HomeFragment;
import com.example.finalhealty.ui.inicio.MainActivity;
import com.example.finalhealty.ui.inicio.Principal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.regex.Pattern;

import retrofit2.Call;

public class PerfilFragment extends Fragment {
    private PerfilViewModel perfilViewModel;
    private EditText nombre, apellido, dni, mail, cumple, pass;
    private ImageView foto;
    private FloatingActionButton editarUsuario,editarPass;
    private Switch sw;
    private Usuario user= MainActivity.usuarioReal;
    private String pass1,pass2,pass3;
    private TextView titulo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);
        ((Principal) getActivity()).setActionBarTitle("Perfil");


        nombre=root.findViewById(R.id.tvnombre);
        dni= root.findViewById(R.id.tvDni);
        mail=root.findViewById(R.id.tvMail);
        cumple=root.findViewById(R.id.tvCumple);
        pass=root.findViewById(R.id.tvPass);
        apellido=root.findViewById(R.id.tvApellido);
        editarUsuario= root.findViewById(R.id.editarUsuario);
        editarPass = root.findViewById(R.id.editarPass);
        sw= root.findViewById(R.id.swFuma);
        foto= root.findViewById(R.id.fotoperfil);
        titulo=root.findViewById(R.id.titulo);

        editarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre.isEnabled()){
                    aceptar();
                    fijarDatos(user);
                    } else {
                    cambiarUsuario();
                }
            }
        });

        editarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CambiarPassDialog();
            }
        });

            perfilViewModel= ViewModelProviders.of(this).get(PerfilViewModel.class);

            fijarDatos(user);

            sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                   if(sw.isChecked()){
                       user.setFumador(1);
                   } else {
                       user.setFumador(0);}
               }
            });
            //

            perfilViewModel.getUsuarioMutableLiveData().observe(this, new Observer<Usuario>() {
                @Override
                public void onChanged(Usuario usuario) {

                    MainActivity.usuarioReal= usuario;

                }
            });

            return root;
        }

    public void aceptar(){
        user.setNombre(nombre.getText().toString());
        user.setApellido(apellido.getText().toString());
        if(sw.isChecked()){
            user.setFumador(1);
        } else {
            user.setFumador(0);
        }
        nombre.setEnabled(false);
        apellido.setEnabled(false);
        sw.setEnabled(false);

        perfilViewModel.putUsuario(user);
        MainActivity.usuarioReal=user;
    }



    public void fijarDatos(Usuario sesion){

        cargarFotoPerfil(sesion.getFotoPerfil());
        nombre.setText(sesion.getNombre());
        apellido.setText(sesion.getApellido());
        dni.setText(sesion.getDni());
        mail.setText(sesion.getMail());
        cumple.setText(sesion.getFecNac());
        pass.setText(String.valueOf(sesion.getPassword()));
        int fuma= sesion.getFumador();
        if(fuma==0){sw.setChecked(false);}else {sw.setChecked(true);}

        editarUsuario.setImageDrawable(getResources().getDrawable(R.drawable.edit));
        ((Principal)getActivity()).setActionBarTitle(sesion.getNombre()+" "+sesion.getApellido());
        titulo.setText(sesion.getNombre()+" "+sesion.getApellido());
    }

    public void cargarFotoPerfil(String url){
        Picasso.with(getActivity()).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .into(foto, new com.squareup.picasso.Callback(){
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                    }
                });
    }

    public void cambiarUsuario() {
            nombre.setEnabled(true);
            apellido.setEnabled(true);
            sw.setEnabled(true);

            editarUsuario.setImageDrawable(getResources().getDrawable(R.drawable.check));
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
                     perfilViewModel.putPassword(pass1,pass2,pass3);
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