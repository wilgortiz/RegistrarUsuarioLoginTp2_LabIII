package com.wilgon.registrarusuariologin.ui.registro;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wilgon.registrarusuariologin.modelo.Usuario;
import com.wilgon.registrarusuariologin.request.ApiClient;
import com.wilgon.registrarusuariologin.ui.login.MainActivity;


public class RegistroActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> mUsuario;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> getMUsuario() {
        if(mUsuario == null){
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }

    public void LeerUsuario(){
        Usuario u = ApiClient.leer(context);
        if( u != null) {
            mUsuario.setValue(u);
        }
    }

    public void GuardarUsuario(String dni, String apellido, String nombre, String email, String password){
        Usuario u = new Usuario(Long.parseLong(dni), apellido, nombre, email, password);
        ApiClient.guardar(context, u);
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
