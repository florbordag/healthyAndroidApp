package com.example.finalhealty.ui.inicio;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    private EditText email,pass;
    private Button boton;
    private TextView error;
    private MainViewModel mainViewModel;
    public static Usuario usuarioReal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel= ViewModelProviders.of(this).get(MainViewModel.class);
        MainActivity.usuarioReal=null;
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        email=findViewById(R.id.et_email);
        pass=findViewById(R.id.et_pass);
        error=findViewById(R.id.error);
        boton=findViewById(R.id.bt_entrar);


        mainViewModel.getError().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {

                error.setVisibility(s);
            }
        });

        mainViewModel.getToken().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                mainViewModel.leer();

            }
        });

        mainViewModel.getUsuarioMutableLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {

                usuarioReal= usuario;


                if(usuarioReal!=null){
                    switch(usuarioReal.getRol()){
                        case "Usuario":
                            Intent i=new Intent(getApplicationContext(),Principal.class);
                            startActivity(i);
<<<<<<< HEAD

                            //finish();
=======
                            finish();
>>>>>>> 8b12206cc14ab541e8e413d141974f5f0f3fa200
                            break;
                        case "Coordinador":
                            Intent c= new Intent(getApplicationContext(), CoordMain.class);
                            startActivity(c);
<<<<<<< HEAD

                            //finish();
=======
                            finish();
>>>>>>> 8b12206cc14ab541e8e413d141974f5f0f3fa200
                            break;
                        case "Administrador":
                            Intent a = new Intent(getApplicationContext(), AdminMain.class);
                            startActivity(a);
<<<<<<< HEAD

                            //finish();
=======
                            finish();
>>>>>>> 8b12206cc14ab541e8e413d141974f5f0f3fa200
                            break;
                    }
                }


            }
        });


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.ingresar(email.getText().toString(),pass.getText().toString());
            }
        });
    }
}