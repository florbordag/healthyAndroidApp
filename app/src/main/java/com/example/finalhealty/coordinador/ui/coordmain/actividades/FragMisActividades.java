package com.example.finalhealty.coordinador.ui.coordmain.actividades;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.finalhealty.R;
import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.ui.actividades.ActividadesViewModel;
import com.example.finalhealty.ui.inicio.MainActivity;

import java.util.List;


public class FragMisActividades extends Fragment {
    private CordActiViewModel cordActiViewModel;
    private View v;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        cordActiViewModel =
                ViewModelProviders.of(this).get(CordActiViewModel.class);
        v = inflater.inflate(R.layout.frag_misactividades, container, false);

        cargarActividades(v);
        cordActiViewModel.getMisActividadesMLD();
        cordActiViewModel.obtenerMisActividades();

        return v;
    }

    public void cargarActividades(final View view){
        cordActiViewModel.getMisActividadesMLD().observe(this, new Observer<List<Actividad>>() {
            @Override
            public void onChanged(List<Actividad> actividads) {
                ArrayAdapter<Actividad> adapter =new ActividadAdapter(getContext(),R.layout.itemactiviti, actividads, getLayoutInflater());
                ListView lv = view.findViewById(R.id.listaMisActividades);
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
            this.context=context;
            this.actividadList=objects;
            this.li=li;
        }


        @NonNull
        @Override
        public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
            View itemView=convertView;
            if(itemView==null){
                itemView=li.inflate(R.layout.itemactiviti,parent,false);
            }
            final Actividad actividad=actividadList.get(position);
            TextView nombre= itemView.findViewById(R.id.name);
            nombre.setText(actividad.getTitulo());
            TextView descripcion=itemView.findViewById(R.id.descripcion);
            descripcion.setText(actividad.getDescripcion());
            TextView horario=itemView.findViewById(R.id.tvHorario);
            horario.setText(actividad.getHorario());
            Button button= itemView.findViewById(R.id.btnAbandonar);
            button.setText("Eliminar");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Eliminar")
                            .setMessage("¿Desea dar de baja esta actividad?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    cordActiViewModel.eliminarActividad();
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
                }});

            return itemView;
        }
    }

}
