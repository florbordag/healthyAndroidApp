package com.example.finalhealty.ui.eventos;

import android.content.Context;
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

import com.example.finalhealty.R;
import com.example.finalhealty.model.Evento;
import com.example.finalhealty.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentMisEventos extends Fragment {
    public static List<Evento> MisEventos= HomeViewModel.misEventos;
    View v;
    public FragmentMisEventos() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.frag_misactividades, container, false);


        ArrayAdapter<Evento> adapter =new com.example.finalhealty.ui.eventos.FragmentMisEventos.EventoAdapter(getContext(),R.layout.itemactiviti,MisEventos, getLayoutInflater() );
        ListView lv = v.findViewById(R.id.listaMisActividades);
        lv.setAdapter(adapter);

        return v;
    }



    //CLASE INTERNA
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
                itemView=li.inflate(R.layout.itemactiviti,parent,false);
            }
            final Evento evento=eventoList.get(position);
            TextView nombre= itemView.findViewById(R.id.name);
            nombre.setText(evento.getTitulo());
            TextView descripcion=itemView.findViewById(R.id.descripcion);
            descripcion.setText(evento.getDescripcion());
            TextView horario=itemView.findViewById(R.id.tvHorario);
            horario.setText(evento.getFechaHora());
            Button button= itemView.findViewById(R.id.btnAbandonar);
            button.setText("Abandonar");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Funciona   "+ evento.getTitulo(),Toast.LENGTH_LONG).show();
                   MisEventos= desinscribirYreordenar(evento);

                }});

            return itemView;
        }
    }




    public List<Evento> desinscribirYreordenar(Evento evento) {

        List<Evento> esta = new ArrayList<>();

        if(HomeViewModel.inscripcionesAEventos!=null && HomeViewModel.inscripcionesAEventos.size()!=0) {
            int x = 0;
            while (x < HomeViewModel.inscripcionesAEventos.size()) {

                if(HomeViewModel.inscripcionesAEventos.get(x).getEvento().getIdEvento()==evento.getIdEvento())
                {
                    HomeViewModel.inscripcionesAEventos.get(x).setEstadoInscripcion(0); }

                x += 1;
            }


            if(HomeViewModel.misEventos.contains(evento)){
                HomeViewModel.misEventos.remove(evento); }

            MisEventos=HomeViewModel.misEventos;

            HomeViewModel.eventosDisponibles.add(evento);

        }

        esta=HomeViewModel.misEventos;
        return esta;
    }


}
