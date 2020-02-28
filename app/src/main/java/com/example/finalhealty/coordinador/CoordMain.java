package com.example.finalhealty.coordinador;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.finalhealty.R;
import com.example.finalhealty.ui.inicio.MainActivity;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class CoordMain extends AppCompatActivity {
    TextView titulo,secundario;
    ImageView imgPerfil;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coord_main_activity);
        dl = findViewById(R.id.drawer_cord);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_cord);
        NavigationView navigationView = findViewById(R.id.nv);
        /////


         NavigationView nv;  nv = findViewById(R.id.nv);
        View hView = nv.getHeaderView(0);
        titulo = hView.findViewById(R.id.titulo_menucord) ;
        imgPerfil = hView.findViewById(R.id.foto_menu_cor);
        secundario= hView.findViewById(R.id.secundario_menucord);

        titulo.setText(MainActivity.usuarioReal.getNombre()+" "+MainActivity.usuarioReal.getApellido());
        secundario.setText(MainActivity.usuarioReal.getMail());
        cargarFotoPerfil(MainActivity.usuarioReal.getFotoPerfil());

         mAppBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.cord_home, R.id.cord_actividades,
                R.id.cord_eventos, R.id.entregarMedalla)
                .setDrawerLayout(drawer)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_cord);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_cord);
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
