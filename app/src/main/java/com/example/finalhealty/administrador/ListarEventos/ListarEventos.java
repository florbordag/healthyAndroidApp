package com.example.finalhealty.administrador.ListarEventos;

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
import android.widget.Toast;

import com.example.finalhealty.R;
import com.example.finalhealty.administrador.AdminMain;
import com.example.finalhealty.administrador.crearYlistarUsuario.UsuarioMainViewModel;
import com.example.finalhealty.model.Evento;

import java.util.List;


public class ListarEventos extends Fragment {
    ListarEventosViewModel listarEventosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listarEventosViewModel=ViewModelProviders.of(this).get(ListarEventosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_listar_eventos, container, false);
        ((AdminMain) getActivity()).setActionBarTitle("ADMIN - Listar Eventos");

        cargarEventos(root);
        listarEventosViewModel.obtenerEventos();

        return root;
    }

    public void cargarEventos(final View view){
        listarEventosViewModel.getEventosMLD().observe(this, new Observer<List<Evento>>() {
            @Override
            public void onChanged(List<Evento> eventos) {
                ArrayAdapter<Evento> adapter =new EventoAdapter(getContext(),R.layout.itemevento, eventos, getLayoutInflater());
                ListView lv = view.findViewById(R.id.listViewEventos);
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
            button.setVisibility(View.GONE);

            return itemView;
        }
    }
}