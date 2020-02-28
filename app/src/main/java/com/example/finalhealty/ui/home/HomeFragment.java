package com.example.finalhealty.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.finalhealty.R;
import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.model.Evento;
import com.example.finalhealty.model.Participante;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.ui.inicio.MainActivity;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Usuario user= MainActivity.usuarioReal;
    private TextView tituloEvento, consejo, nohayActis;
    private TextView descripEvento;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        tituloEvento = root.findViewById(R.id.tituloProxEvento);
        descripEvento= root.findViewById(R.id.horaProxEvento);
        consejo = root.findViewById(R.id.consejo_home);
        nohayActis=root.findViewById(R.id.tvNohayActis);
        cargarActividades(root);
        cargarEventos(root);

        homeViewModel.obtenerMisActividades();
        homeViewModel.obtenerMisEventos();

        return root;
    }

    public void cargarActividades(final View view){
        homeViewModel=ViewModelProviders.of(this).get(homeViewModel.getClass());
        homeViewModel.getMiActividadMutableLiveData().observe(this, new Observer<List<Actividad>>() {
            @Override
            public void onChanged(List<Actividad> actividads) {

                     nohayActis.setVisibility(View.INVISIBLE);


                ArrayAdapter<Actividad> adapter =new ListaAdapter(getContext(),R.layout.short_item, actividads, getLayoutInflater() );
                ListView lv = view.findViewById(R.id.listaActividades);
                lv.setAdapter(adapter);
            }
        });

    }
    public void cargarEventos(final View view){
        homeViewModel=ViewModelProviders.of(this).get(homeViewModel.getClass());
        homeViewModel.getMisEventosMLD().observe(this, new Observer<List<Evento>>() {
            @Override
            public void onChanged(List<Evento> eventos) {
                if(eventos.size()>0){
                    tituloEvento.setText(eventos.get(0).getTitulo());
                    descripEvento.setText(eventos.get(0).getFechaHora());
                } else {
                    tituloEvento.setText("No está inscripto en ningun evento.");
                }
            }
        });

    }



    public class ListaAdapter extends ArrayAdapter<Actividad> {
        private Context context;
        private List<Actividad> actividadList;
        private LayoutInflater li;


        public ListaAdapter(@NonNull Context context, int resource, @NonNull List<Actividad> objects, LayoutInflater li) {
            super(context, resource, objects);
            this.context=context;
            this.actividadList=objects;
            this.li=li;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
            View itemView=convertView;
            if(itemView==null){
                itemView=li.inflate(R.layout.short_item,parent,false);
            }
            Actividad actividad=actividadList.get(position);
            TextView nombre= itemView.findViewById(R.id.nameactivity);
            nombre.setText(actividad.getTitulo());
            TextView horario=itemView.findViewById(R.id.tvhorarioAct);
            horario.setText(actividad.getHorario());

            nombre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(getContext(), "hacer q redirija a Actividades", Toast.LENGTH_LONG).show();
                }
            });

            return itemView;
        }


    }


}

