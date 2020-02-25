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
import com.example.finalhealty.model.Participante;
import com.example.finalhealty.ui.home.HomeViewModel;
import com.example.finalhealty.ui.inicio.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.http2.Hpack;

public class FragmentEventosDisponibles extends Fragment {
    public static List<Evento> EventosDisponibles= HomeViewModel.eventosDisponibles;
    View v;
    public FragmentEventosDisponibles() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.frag_dispoactividades, container, false);


        ArrayAdapter<Evento> adapter =new com.example.finalhealty.ui.eventos.FragmentEventosDisponibles.EventoAdapter(getContext(),R.layout.itemactiviti,EventosDisponibles, getLayoutInflater() );
        ListView lv = v.findViewById(R.id.listaDeActividadesDisponibles);
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
            button.setText("Participar");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 //   Toast.makeText(getActivity(), "Funciona   "+ evento.getTitulo(),Toast.LENGTH_LONG).show();
                    EventosDisponibles=InscribirYreordenar(evento);

                }});

            return itemView;
        }
    }

    public List<Evento> InscribirYreordenar(Evento evento) {

        List<Evento> esta = new ArrayList<>();
         //  if(HomeViewModel.inscripcionesAEventos.size()==0){HomeViewModel.inscripcionesAEventos=null;}
        // if(EventosDisponibles.size()==0){EventosDisponibles=null;}
        // if(HomeViewModel.eventosDisponibles.size()==0){HomeViewModel.eventosDisponibles=null;}
          if(HomeViewModel.inscripcionesAEventos!=null && HomeViewModel.inscripcionesAEventos.size()!=0) {
            int x = 0;
            while (x < HomeViewModel.inscripcionesAEventos.size()) {

                if(HomeViewModel.inscripcionesAEventos.get(x).getEvento().getIdEvento()==evento.getIdEvento())
                {
                    HomeViewModel.inscripcionesAEventos.get(x).setEstadoInscripcion(1); }


                // else{}
                x += 1;
            }

            if(!HomeViewModel.inscripcionesAEventos.contains(evento)){
                Participante pp= new Participante(MainViewModel.user, evento.getActividad(),"20/02/2020",1);
                HomeViewModel.participaciones.add(pp);
            }

            if(HomeViewModel.eventosDisponibles.contains(evento)){ Toast.makeText(getActivity(), "FuncionaDI   "+ evento.getTitulo(),Toast.LENGTH_LONG).show();}


            if(HomeViewModel.eventosDisponibles.contains(evento)) {
                HomeViewModel.eventosDisponibles.remove(evento);}

            EventosDisponibles=HomeViewModel.eventosDisponibles;

            HomeViewModel.misEventos.add(evento);


        }



        esta= HomeViewModel.eventosDisponibles;
        return esta;
    }
}
