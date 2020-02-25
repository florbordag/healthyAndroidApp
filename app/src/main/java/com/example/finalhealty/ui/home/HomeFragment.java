package com.example.finalhealty.ui.home;

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

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.finalhealty.R;
import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.model.Participante;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.ui.inicio.MainActivity;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Usuario user= MainActivity.usuarioReal;
    TextView tituloEvento;
    TextView descripEvento;
    public static List<Participante> listaParticipante= HomeViewModel.participaciones;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel.getUsuarioMutableLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {


            }
        });

        ArrayAdapter<Actividad> adapter =new ListaAdapter(getContext(),R.layout.short_item, HomeViewModel.shortAct, getLayoutInflater() );
        ListView lv = root.findViewById(R.id.listaActividades);
        lv.setAdapter(adapter);

        tituloEvento = root.findViewById(R.id.tituloProxEvento);
        descripEvento= root.findViewById(R.id.horaProxEvento);



        if(HomeViewModel.mostrarEvento!=null){tituloEvento.setText(HomeViewModel.mostrarEvento.getTitulo());
            descripEvento.setText(HomeViewModel.mostrarEvento.getFechaHora());}
        else {tituloEvento.setText("No está inscripto en ningun evento.");}



    return root;
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
            Button nombre= itemView.findViewById(R.id.nameactivity);
            nombre.setText(actividad.getNombreActividad());
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

