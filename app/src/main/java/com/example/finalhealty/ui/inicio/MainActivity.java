package com.example.finalhealty.ui.inicio;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalhealty.R;
import com.example.finalhealty.coordinador.CoordMain;
import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.model.Evento;
import com.example.finalhealty.model.InscripcionAEvento;
import com.example.finalhealty.model.Participante;
import com.example.finalhealty.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private TextView error;
    private String token;
    private MainViewModel mainViewModel;
    //variables del USUARIO que voy a necesitar:
    public static Usuario usuarioReal;
    public static List<Actividad> ListaActividades= new ArrayList<>();
    public static List<Actividad> ListaActividadesDisponibles= new ArrayList<>();
    public static List<Evento> EventosDisponibles= new ArrayList<>();
    public static List<Actividad> ListaMisActividades = new ArrayList<>();
    public static List<Evento> ListaMisEventos = new ArrayList<>();
    public static List<Participante> ListaParticipantes= new ArrayList<>();
    public static List<InscripcionAEvento> ListaInscripcionEvento= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.et_email);
        pass=findViewById(R.id.et_pass);
        error=findViewById(R.id.error);

        mainViewModel= ViewModelProviders.of(this).get(MainViewModel.class);

        mainViewModel.getError().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {

                error.setVisibility(s);
            }
        });

        mainViewModel.getToken().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                if(usuarioReal!=null&&usuarioReal.getRol().equals("Usuario")){
                    Intent i=new Intent(getApplicationContext(),Principal.class);
                    startActivity(i);
                } else if (usuarioReal!=null&&usuarioReal.getRol().equals("Coordinador")){
                    Intent i= new Intent(getApplicationContext(), CoordMain.class);
                    startActivity(i);
                } else if(usuarioReal!=null){
                    //admin
                }
            }
        });
        mainViewModel.getUsuarioMutableLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {

                usuarioReal= usuario;
                if(usuario.getFotoPerfil()==null){usuarioReal.setFotoPerfil("https://cdnlrv.lavoz.com.ar/img/user/default.jpg");}


            }
        });


















    }
    public void ingresar(android.view.View view){

        mainViewModel.ingresar(email.getText().toString(),pass.getText().toString());
    }


}
