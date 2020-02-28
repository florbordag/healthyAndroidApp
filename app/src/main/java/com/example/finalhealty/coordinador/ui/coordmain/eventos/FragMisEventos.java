package com.example.finalhealty.coordinador.ui.coordmain.eventos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.finalhealty.R;
import com.example.finalhealty.model.Evento;

import java.util.List;

public class FragMisEventos extends Fragment {
    private CordEventoViewModel cordEventoViewModel;
    private View v;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        cordEventoViewModel =
                ViewModelProviders.of(this).get(CordEventoViewModel.class);
        v = inflater.inflate(R.layout.frag_miseventos_coord, container, false);

        cargarEventos(v);
        cordEventoViewModel.getEventosMTL();
        cordEventoViewModel.obtenerMisEventos();

        return v;
    }

    public void cargarEventos(final View view){
        cordEventoViewModel.getEventosMTL().observe(this, new Observer<List<Evento>>() {
            @Override
            public void onChanged(List<Evento> eventos) {
                ArrayAdapter<Evento> adapter =new FragMisEventos.EventoAdapter(getContext(),R.layout.itemevento, eventos, getLayoutInflater());
                ListView lv = view.findViewById(R.id.listViewMisEventosCoord);
                lv.setAdapter(adapter);
            }
        });
    }


    public class EventoAdapter extends ArrayAdapter<Evento> {
        private Context context;
        private List<Evento> eventoList;
        private LayoutInflater li;


        public EventoAdapter(@NonNull Context context, int resource, @NonNull List<Evento> objects, LayoutInflater li) {
            super(context, resource, objects);
            this.context=context;
            this.eventoList=objects;
            this.li=li;
        }


        @NonNull
        @Override
        public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
            View itemView=convertView;
            if(itemView==null){
                itemView=li.inflate(R.layout.itemevento,parent,false);
            }
            final Evento evento=eventoList.get(position);
            TextView nombre= itemView.findViewById(R.id.tituloEvento);
            nombre.setText(evento.getTitulo());
            TextView descripcion=itemView.findViewById(R.id.descripcionEvento);
            descripcion.setText(evento.getDescripcion());
            TextView horario=itemView.findViewById(R.id.horarioEvento);
            horario.setText(evento.getFechaHora());
            Button button= itemView.findViewById(R.id.btnAbandonarEvento);
            button.setText("Dar de baja");

            if(evento.getEstado()==0){
                nombre.setTextColor(getResources().getColor(R.color.light));
                descripcion.setTextColor(getResources().getColor(R.color.light));
                horario.setTextColor(getResources().getColor(R.color.light));
                button.setVisibility(View.GONE);
            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Abandonar")
                            .setMessage("¿Desea abandonar el evento "+evento.getTitulo()+"?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    cordEventoViewModel.darDeBaja(evento);
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