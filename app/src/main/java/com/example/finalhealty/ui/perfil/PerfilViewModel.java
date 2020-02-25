package com.example.finalhealty.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalhealty.ShowToast;
import com.example.finalhealty.model.Usuario;
import com.example.finalhealty.request.ApiClient;
import com.example.finalhealty.ui.home.HomeFragment;
import com.example.finalhealty.ui.inicio.MainActivity;
import com.example.finalhealty.ui.inicio.MainViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
   // private Context context;
  MutableLiveData<Usuario> usuarioMutableLiveData;
  Usuario user= MainActivity.usuarioReal;
  Context context;
  SharedPreferences sp;
  MutableLiveData<String> error;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context= getApplication().getApplicationContext();
         sp= context.getSharedPreferences("token",0);

         //Agregar dialogo de confirmacion
    }

    public MutableLiveData<Usuario> getUsuarioMutableLiveData(){
        if(usuarioMutableLiveData==null){
            usuarioMutableLiveData=new MutableLiveData<>();
        }
        return usuarioMutableLiveData;
    }
    public MutableLiveData<String> getErrorMutableLiveData(){
        if(error==null){
            error=new MutableLiveData<>();
        }
        return error;
    }


    public void putPassword(String pass1,String pass2, String pass3) {
        if(!pass2.equals(pass3)){
            error.postValue("Las claves deben coincidir");
        } else{
           Call<Usuario> dato = ApiClient.getMyApiClient().putClaveUsuario(
                    sp.getString("token",""),
                    user.getId(),
                    pass1, pass2 );

            dato.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        usuarioMutableLiveData.postValue(response.body());
                        new ShowToast(context, "Actualización exitosa");
                    } else {
                        new ShowToast(context, response.message()+ ". Problema en putPassword");
                    }
                }
                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {

                    new ShowToast(context, t.getMessage());
                }
            });
        }
    }

    public void putUsuario(Usuario usuario){

        Call<Usuario> dato = ApiClient.getMyApiClient().putUsuario(
                sp.getString("token",""),
                usuario.getId(),
                usuario.getEmpresa(),usuario.getNombre(),usuario.getApellido(),usuario.getMail(),usuario.getPassword(),usuario.getDni(),usuario.getRol(),usuario.getConducta(),usuario.getFecNac(),usuario.getFumador(),usuario.getFechaUltMod(),usuario.getFumaUltMod(),usuario.getAdminUltModId());

        dato.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    usuarioMutableLiveData.postValue(response.body());
                    new ShowToast(context, "Actualización exitosa");
                } else {
                    new ShowToast(context, response.message()+ ". Problema en putUsuario");
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }

}
