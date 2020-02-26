package com.example.finalhealty.request;

import android.util.Log;

import com.example.finalhealty.model.Actividad;
import com.example.finalhealty.model.Evento;
import com.example.finalhealty.model.Participante;
import com.example.finalhealty.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ApiClient {   //https://healthy2020-ih7.conveyor.cloud/
    private static final String PATH="https://healthy2020.conveyor.cloud/Api/";
    private static  MyApiInterface myApiInteface;

    public static MyApiInterface getMyApiClient(){
        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        myApiInteface=retrofit.create(MyApiInterface.class);
        Log.d("salida",retrofit.baseUrl().toString());
        return myApiInteface;
    }


    public interface MyApiInterface {

        // Todes //

        @POST("Usuario/Login")
        Call<String> login(@Query("Mail") String mail, @Query("Password") String password);

        @GET("Usuario")
        Call<Usuario> leer(@Header("Authorization")String token);

        @PUT("Usuario/CambiarPass/{id}")
        Call<Usuario> putClaveUsuario(
                @Header("Authorization")String token,
                @Path("id") int id,
                @Query("pass1") String pass1, @Query("pass2") String pass2);

        @FormUrlEncoded
        @PUT("Usuario/{id}")
        Call<Usuario> putUsuario(
                @Header("Authorization")String token,
                @Path("id") int id,
                @Field ("Empresa") int Empresa,
                @Field("Nombre") String Nombre,
                @Field("Apellido") String Apellido,
                @Field("Mail") String Mail,
                @Field("Password") String Password,
                @Field("Dni") String Dni,
                @Field("Rol") String Rol,
                @Field("Conducta") int Conducta,
                @Field("FecNac") String FecNac,
                @Field("Fumador") int Fumador,
                @Field("FechaUltMod") String FechaUltMod,
                @Field("FumaUltMod") String FumaUltMod,
                @Field("AdminUltModId") int AdminUltMod );


        /////////USUARIOS//////////

        //Eventos
        @GET("Evento/Usuario/mias")
        Call<List<Evento>> getMisEventos(@Header("Authorization")String token);

        @GET("Evento/Usuario/mi")
        Call<List<Evento>> getEventosDisponibles(@Header("Authorization")String token);

        //@POST("Inscripcion/Evento")
        //Call

        //Actividades
        @GET("Actividad/Usuario/mias")
        Call<List<Actividad>> getMisActividades(@Header("Authorization")String token);

        @GET("Actividad/Usuario/mi")
        Call<List<Actividad>> getActividadesDisponibles(@Header("Authorization")String token);

        @POST("Participante")
        Call<Actividad> participar(@Header("Authorization")String token, @Query("ActividadId") int actividadId);

        @GET("Participante")
        Call<Integer> idParticipante(@Header("Authorization")String token, @Query("usuario") int usuario,@Query("actividad") int actividad);

        @DELETE("Participante/{id}")
        Call<Participante> abandonar(@Header("Authorization")String token, @Path("id") int id, @Query("Estado") int estado);

        //Medallas


        ///////COORDINADORES////////

        //Eventos
        @GET("Evento/Coordinador/mias")
        Call<List<Evento>> getMisEventosCoord(@Header("Authorization")String token);

        @PUT("evento/{id}")
        Call<Evento> putEvento(
                @Header("Authorization")String token,
                @Path("id") int id,
                @Body Evento evento);


        //Actividades
        @GET("Actividad/Coordinador/mias")
        Call<List<Actividad>> getMisActividadesCoord(@Header("Authorization")String token);

        @GET("Actividad/Coordinador/mi")
        Call<List<Evento>> getActividadesDisponiblesCoord(@Header("Authorization")String token);

        @PUT("actividad/{id}")
        Call<Evento> putEvento(
                @Header("Authorization")String token,
                @Path("id") int id,
                @Body Actividad actividad);


        ///////ADMINISTRADORES////////

        //Usuarios
        @FormUrlEncoded
        @POST("ListenPostUsuario.php")
        Call<List<Usuario>> login2(@Field("Usuariologuearapp") String paramLogi);

        //Eventos
        @GET("Evento/Coordinador/mias")
        Call<List<Evento>> getEventos(@Header("Authorization")String token);

        //Actividades
        @GET("Actividad/Coordinador/mias")
        Call<List<Actividad>> getActividades(@Header("Authorization")String token);


    }

}
