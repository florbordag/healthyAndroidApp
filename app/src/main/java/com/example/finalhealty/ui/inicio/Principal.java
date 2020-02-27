package com.example.finalhealty.ui.inicio;

import android.os.Bundle;

import com.example.finalhealty.R;
import com.example.finalhealty.ShowToast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.transition.Transition;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class Principal extends AppCompatActivity {
    private TextView titulo,secundario;
    private ImageView imgPerfil;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
        titulo = hView.findViewById(R.id.titulo);
        imgPerfil = hView.findViewById(R.id.imgPerfil);

        titulo.setText(MainActivity.usuarioReal.getNombre()+" "+MainActivity.usuarioReal.getApellido());
        cargarFotoPerfil(MainActivity.usuarioReal.getFotoPerfil());

        secundario = hView.findViewById(R.id.secundario);
        secundario.setText(MainActivity.usuarioReal.getMail());
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_perfil, R.id.nav_actividades,
                    R.id.nav_eventos, R.id.nav_medallas, R.id.nav_logout)
                    .setDrawerLayout(drawer)
                    .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public void cargarFotoPerfil(String url){
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .into(imgPerfil, new com.squareup.picasso.Callback(){
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }


}


