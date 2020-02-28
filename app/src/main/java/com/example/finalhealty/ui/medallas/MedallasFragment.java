package com.example.finalhealty.ui.medallas;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.finalhealty.R;
import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.model.MedallaVirtual;

import java.util.List;

public class MedallasFragment extends Fragment {

    private MedallasViewModel medallasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        medallasViewModel =
                ViewModelProviders.of(this).get(MedallasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_medallas, container, false);

        listarMedallas(root);
        medallasViewModel.obtener();

        return root;
    }

    public void listarMedallas(final View view){
        medallasViewModel.getMedallasMLD().observe(this, new Observer<List<MedallaVirtual>>() {
            @Override
            public void onChanged(List<MedallaVirtual> medallaVirtuals) {
                ArrayAdapter<MedallaVirtual> adapter= new MedallaAdapter(getContext(),R.layout.itemmeda,medallaVirtuals,getLayoutInflater());
                ListView lv= view.findViewById(R.id.listaSusMedallas);
                lv.setAdapter(adapter);
            }
        });
    }


    //CLASE INTERNA
    public class MedallaAdapter extends ArrayAdapter<MedallaVirtual> {
        private Context context;
        private List<MedallaVirtual> medallas;
        private LayoutInflater li;


        public MedallaAdapter(@NonNull Context context, int resource, @NonNull List<MedallaVirtual> objects, LayoutInflater li) {
            super(context, resource, objects);
            this.context=context;
            this.medallas=objects;
            this.li=li;
        }
        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView=convertView;
            if(itemView==null){
                itemView=li.inflate(R.layout.itemmeda,parent,false);
            }
            final MedallaVirtual medallaVirtual=medallas.get(position);
            TextView nombre= itemView.findViewById(R.id.ivMedallaN);
            nombre.setText(medallaVirtual.getNombre());
            TextView descripcion=itemView.findViewById(R.id.ivMedalla1D);
            descripcion.setText(medallaVirtual.getDescripcion());
            ImageView color=itemView.findViewById(R.id.ivMedalla1);

            switch(medallaVirtual.getColor()){
                case "Oro":
                    color.setForeground(getResources().getDrawable(R.drawable.oro));
                    break;
                case "Plata":
                    color.setForeground(getResources().getDrawable(R.drawable.plata));
                    break;
                case "Bronce":
                    color.setForeground(getResources().getDrawable(R.drawable.bronce));
                    break;
            }

            return itemView;
        }
    }


}