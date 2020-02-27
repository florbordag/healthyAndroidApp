package com.example.finalhealty.administrador;

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
import com.google.android.material.navigation.NavigationView;

public class AdminMain extends AppCompatActivity {
    TextView titulo,secundario;
    ImageView imgPerfil;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_acivity);
        dl = findViewById(R.id.drawer_admin);
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_admin);
        NavigationView navigationView = findViewById(R.id.nv_admin);
        /////


        NavigationView nv;  nv = findViewById(R.id.nv_admin);
        View hView = nv.getHeaderView(0);
        titulo = hView.findViewById(R.id.titulo_menuadmin) ;
        imgPerfil = hView.findViewById(R.id.foto_menu_admin);
        secundario= hView.findViewById(R.id.secundario_menuadmin);


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.home_admin, R.id.crearUsuario,R.id.listarUsuarios,R.id.listarActividades,
                R.id.listarEventos, R.id.logout_admin)
                .setDrawerLayout(drawer)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_admin);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_admin);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}

