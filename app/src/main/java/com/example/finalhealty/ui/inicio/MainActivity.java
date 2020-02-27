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
import com.example.finalhealty.administrador.AdminMain;
import com.example.finalhealty.coordinador.CoordMain;
import com.example.finalhealty.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private TextView error;
    private String token;
    private MainViewModel mainViewModel;
    public static Usuario usuarioReal;


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

                if(usuarioReal!=null){
                    switch(usuarioReal.getRol()){
                        case "Usuario":
                            Intent i=new Intent(getApplicationContext(),Principal.class);
                            startActivity(i);
                            break;
                        case "Coordinador":
                            Intent c= new Intent(getApplicationContext(), CoordMain.class);
                            startActivity(c);
                            break;
                        case "Administrador":
                            Intent a = new Intent(getApplicationContext(), AdminMain.class);
                            startActivity(a);
                            break;
                    }
                }
            }
        });
        mainViewModel.getUsuarioMutableLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {

                usuarioReal= usuario;
                if(usuario.getFotoPerfil()==null){usuarioReal.setFotoPerfil("https://www.shantiyoga.com.au/wp-content/uploads/default-avatar3.png");}


            }
        });


















    }
    public void ingresar(android.view.View view){

        mainViewModel.ingresar(email.getText().toString(),pass.getText().toString());
    }


}