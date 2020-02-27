package com.example.finalhealty.administrador.crearYlistarUsuario;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.finalhealty.R;
import com.example.finalhealty.administrador.AdminMain;
import com.example.finalhealty.administrador.Home.HomeAdminViewModel;
import com.example.finalhealty.coordinador.ui.coordmain.actividades.FragMisActividades;
import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.ui.home.HomeViewModel;

import java.util.List;


public class ListarUsuarios extends Fragment {
    private UsuarioMainViewModel usuarioMainViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        usuarioMainViewModel=ViewModelProviders.of(this).get(UsuarioMainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_listar_usuarios, container, false);
        ((AdminMain) getActivity()).setActionBarTitle("ADMIN - Listar Usuarios");


        cargarUsuarios(root);
        usuarioMainViewModel.obtenerUsuarios();

        return root;
    }

    public void cargarUsuarios(final View view){

        usuarioMainViewModel.getUsuariosMLD().observe(this, new Observer<List<Usuario>>() {
            @Override
            public void onChanged(List<Usuario> users) {
                ArrayAdapter<Usuario> adapter =new ListarUsuarios.UsuarioAdapter(getContext(),R.layout.itemusuario, users, getLayoutInflater());
                ListView lv = view.findViewById(R.id.listViewUsuarios);
                lv.setAdapter(adapter);
            }
        });
    }


    public class UsuarioAdapter extends ArrayAdapter<Usuario> {
        private Context context;
        private List<Usuario> usuarioList;
        private LayoutInflater li;


        public UsuarioAdapter(@NonNull Context context, int resource, @NonNull List<Usuario> objects, LayoutInflater li) {
            super(context, resource, objects);
            this.context=context;
            this.usuarioList=objects;
            this.li=li;
        }


        @NonNull
        @Override
        public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
            View itemView=convertView;
            if(itemView==null){
                itemView=li.inflate(R.layout.itemusuario,parent,false);
            }
            final Usuario usuario=usuarioList.get(position);
            ImageView icono= itemView.findViewById(R.id.iconoTipoUsuario);

            TextView nombre= itemView.findViewById(R.id.tvNombreListarUser);
            nombre.setText(usuario.getNombre()+" "+usuario.getApellido());
            TextView mail=itemView.findViewById(R.id.tvMailListarUser);
            mail.setText(usuario.getMail());
            LinearLayout background = itemView.findViewById(R.id.backgrounditem);

            switch (usuario.getRol()){
                case "Administrador":
                    background.setBackgroundColor(getResources().getColor(R.color.backgroundAdmin));
                    icono.setImageResource(R.drawable.user_otro);
                    break;
                case "Coordinador":
                    background.setBackgroundColor(getResources().getColor(R.color.backgroundCoor));
                    icono.setImageResource(R.drawable.user_otromas);
                    break;
                case "Usuario":
                    background.setBackgroundColor(getResources().getColor(R.color.backgroundUser));
                    icono.setImageResource(R.drawable.user_generic);
                    break;
            }

            return itemView;
        }
    }
}
