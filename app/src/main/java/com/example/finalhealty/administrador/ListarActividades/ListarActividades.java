package com.example.finalhealty.administrador.ListarActividades;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.finalhealty.R;
import com.example.finalhealty.administrador.AdminMain;
import com.example.finalhealty.administrador.crearYlistarUsuario.UsuarioMainViewModel;
import com.example.finalhealty.model.Actividad;

import java.util.List;


public class ListarActividades extends Fragment {
    ListarActividadesViewModel listarActividadesViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listarActividadesViewModel= ViewModelProviders.of(this).get(ListarActividadesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_listar_actividades, container, false);
        ((AdminMain) getActivity()).setActionBarTitle("ADMIN - Listar Actividades");

        cargarActividades(root);
        listarActividadesViewModel.obtenerActividades();

        return root;
    }

    public void cargarActividades(final View view){
        listarActividadesViewModel.getActividadesMLD().observe(this, new Observer<List<Actividad>>() {
            @Override
            public void onChanged(List<Actividad> actividads) {
                ArrayAdapter<Actividad> adapter =new ActividadAdapter(getContext(),R.layout.itemactiviti, actividads, getLayoutInflater());
                ListView lv = view.findViewById(R.id.listViewActividades);
                lv.setAdapter(adapter);
            }
        });
    }

    public class ActividadAdapter extends ArrayAdapter<Actividad> {
        private Context context;
        private List<Actividad> actividadList;
        private LayoutInflater li;


        public ActividadAdapter(@NonNull Context context, int resource, @NonNull List<Actividad> objects, LayoutInflater li) {
            super(context, resource, objects);
            this.context = context;
            this.actividadList = objects;
            this.li = li;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = li.inflate(R.layout.itemactiviti, parent, false);
            }
            final Actividad actividad = actividadList.get(position);
            TextView nombre = itemView.findViewById(R.id.name);
            nombre.setText(actividad.getTitulo());
            TextView descripcion = itemView.findViewById(R.id.descripcion);
            descripcion.setText(actividad.getDescripcion());
            TextView horario = itemView.findViewById(R.id.tvHorario);
            horario.setText(actividad.getHorario());
            Button button = itemView.findViewById(R.id.btnAbandonar);
            button.setVisibility(View.GONE);

            return itemView;
        }
    }

}