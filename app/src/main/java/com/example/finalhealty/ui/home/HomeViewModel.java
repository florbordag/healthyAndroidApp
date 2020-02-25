package com.example.finalhealty.ui.home;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.model.Evento;
import com.example.finalhealty.model.InscripcionAEvento;
import com.example.finalhealty.model.Participante;
import com.example.finalhealty.model.Predio;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.request.ApiClient;
import com.example.finalhealty.ui.inicio.MainViewModel;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {
    public static List<Participante> participaciones;
    public static List<Actividad> misActividades;
    public static List<Actividad> dispoActividades;
    public static List<InscripcionAEvento> inscripcionesAEventos;
    public static List<Evento> misEventos;
    public static List<Evento> eventosDisponibles;
    public static Evento mostrarEvento;
    private MutableLiveData<Actividad> actividadMutableLiveData;
    public static List<Actividad> shortAct;
    MutableLiveData<Usuario> usuarioMutableLiveData;
    Context context;
    SharedPreferences sp;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        sp= context.getSharedPreferences("token",0);
    }


    public MutableLiveData<Usuario> getUsuarioMutableLiveData(){
        if(usuarioMutableLiveData==null){
            usuarioMutableLiveData=new MutableLiveData<>();
        }


        if(participaciones==null){participaciones=obtenerParticipantes();}

        misActividades=obtenerMisActividades();
        dispoActividades=obtenerDisponibles();
        shortAct=reducir();


        if(inscripcionesAEventos==null){inscripcionesAEventos=obtenerInscripcionAEventos();}

        misEventos=obtenerMisEventos(inscripcionesAEventos);
        eventosDisponibles=obtenerEventosDisponibes(inscripcionesAEventos);
        mostrarEvento=obtenerEventoHome(misEventos);
        return usuarioMutableLiveData;
    }



    public LiveData<Actividad> getActividadesMutableLiveData(){
        if(actividadMutableLiveData==null){
            actividadMutableLiveData=new MutableLiveData<>();
        }
        return actividadMutableLiveData;
    }
    public List<Participante> obtenerParticipantes() {
        //FALSE METHOD



        //TRUE METHOD : obtener participantes donde idParticipante == user.id Y estado==1

        /*String parametro="[{\"id\":\""+"\" ,\"password\":\""+"\"}]";
        Call<List<Participante>> dato= ApiClient.getMyApiClient().listarParticipacion(parametro);
        dato.enqueue(new Callback<List<Participante>>() {
            @Override
            public void onResponse(Call<List<Participante>> call, Response<List<Participante>> response) {
                if(response.isSuccessful()){

                    misActividadesMLD.postValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Participante>> call, Throwable t) {
                Log.d("salida huesped",t.getMessage());
            }
        });*/

        List<Participante> esta = new ArrayList<>();
        return esta;


    }

    public List<Actividad> obtenerMisActividades() {
        int x = 0;
        List<Actividad> esta = new ArrayList<>();

        if(participaciones!=null&& participaciones.size()!=0) {

            while (x < participaciones.size()) {

                if(participaciones.get(x).getEstadoParticipante()==1)
                {  esta.add(participaciones.get(x).getActividad());}


                x += 1;
            }

        } return esta;
    }

    public List<Actividad> obtenerDisponibles() {
        int x = 0;
        List<Actividad> otra = new ArrayList<>();

        if(participaciones!=null&& participaciones.size()!=0) {

            while (x < participaciones.size()) {

                if(participaciones.get(x).getEstadoParticipante()==0)
                {  otra.add(participaciones.get(x).getActividad());}


                x += 1;
            }

        } return otra;
    }
    public List<Actividad> reducir(){

        List<Actividad> s = new ArrayList<>();
        if(misActividades!=null){
            int x=0;
            while (x<misActividades.size()&& x<3){

                s.add(misActividades.get(x));x+=1;}
        }

        return s;
    }

    public List<InscripcionAEvento> obtenerInscripcionAEventos(){
        //OBTENER TODAS LAS INSCRIPCIONES A EVENTOS, ORDENADAS POR FECHA DE EVENTO
        List<InscripcionAEvento> ins = new ArrayList<>();

        return ins;
    }

    public List<Evento> obtenerMisEventos(@NotNull List<InscripcionAEvento> lista){
        List<Evento> e= new ArrayList<>();

        for(int x=0;x<lista.size();x++){
            if(lista.get(x).getEstadoInscripcion()==1){ e.add(lista.get(x).getEvento());}
        }
        return e;
    }
    public List<Evento> obtenerEventosDisponibes (@NotNull List<InscripcionAEvento> lis){
        List<Evento> e= new ArrayList<>();

        for(int x=0;x<lis.size();x++){

            if(lis.get(x).getEstadoInscripcion()==0){ e.add(lis.get(x).getEvento());}
        }
        return e;
    }


    public Evento obtenerEventoHome(List<Evento> eventos){
        Evento e=null;

       if(eventos!=null&&eventos.size()!=0){
        e=eventos.get(0);
           try{
               SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // here set the pattern as you date in string was containing like date/month/year
               Date d1 = sdf.parse(e.getFechaHora());


               for (int x=0;x< eventos.size();x++) {
                   Date d2 = sdf.parse(eventos.get(x).getFechaHora());
                   if(d1.after(d2)){
                       e=eventos.get(x);
                   }
               }

           }catch(ParseException ex){
               // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
           }
        }
       return e;
    }






}