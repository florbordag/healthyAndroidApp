package com.example.finalhealty.ui.actividades;

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
import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentActividadesDisponibles extends Fragment {
    Actividad quitarAct=null;
    ActividadesViewModel actividadesViewModel;
    View v ;
    public FragmentActividadesDisponibles() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.frag_dispoactividades, container, false);

        List<Actividad> actividadesDisponibles=actividadesViewModel.obtenerDisponibles();
        ArrayAdapter<Actividad> adapter =new ActividadAdapter(getContext(), R.layout.itemactiviti,actividadesDisponibles, getLayoutInflater() );
        ListView lv = v.findViewById(R.id.listaDeActividadesDisponibles);
        lv.setAdapter(adapter);

        return v;
    }



    //CLASE INTERNA
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
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView=convertView;
            if(itemView==null){
                itemView=li.inflate(R.layout.itemactiviti,parent,false);
            }
            final Actividad actividad=actividadList.get(position);
            TextView nombre= itemView.findViewById(R.id.name);
            nombre.setText(actividad.getNombreActividad());
            TextView descripcion=itemView.findViewById(R.id.descripcion);
            descripcion.setText(actividad.getDescripcionActividad());
            TextView horario=itemView.findViewById(R.id.tvHorario);
            horario.setText(actividad.getHorario());
           Button button= itemView.findViewById(R.id.btnAbandonar);
            button.setText("Inscribirse");


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Toast.makeText(getActivity(), "Inscribirse a   "+ actividad.getNombreActividad(),Toast.LENGTH_LONG).show();

                   InscribirYreordenar(actividad);

                }

            });



            return itemView;
        }
    }


    public List<Actividad> InscribirYreordenar(Actividad actividad) {

        List<Actividad> esta = new ArrayList<>();

        if(HomeViewModel.participaciones!=null &&HomeViewModel.participaciones.size()!=0) {
            int x = 0;
                while (x < HomeViewModel.participaciones.size()) {

                    if(HomeViewModel.participaciones.get(x).getActividad().getIdActividad()==actividad.getIdActividad())
                    {
                        HomeViewModel.participaciones.get(x).setEstadoParticipante(1); }
                   // else{Participante pp= new Participante(MainViewModel.user, actividad,"20/02/2020",1);
                     //   ActividadesViewModel.participaciones.add(pp);}
                    x += 1;
                }


            if(HomeViewModel.dispoActividades.contains(actividad)) {
                HomeViewModel.dispoActividades.remove(actividad);}

            //actividades=HomeViewModel.dispoActividades;

            HomeViewModel.misActividades.add(actividad);


        }

        esta= HomeViewModel.dispoActividades;
        return esta;
    }

}
