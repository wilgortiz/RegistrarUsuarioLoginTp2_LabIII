

package com.wilgon.registrarusuariologin.ui.registro;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.wilgon.registrarusuariologin.databinding.ActivityRegistroBinding;
import com.wilgon.registrarusuariologin.modelo.Usuario;


public class RegistroActivity extends AppCompatActivity {

    private RegistroActivityViewModel mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegistroBinding binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        mv.getMUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etDni.setText(String.valueOf(usuario.getDni()));
                binding.etApellido.setText(usuario.getApellido());
                binding.etNombre.setText(usuario.getNombre());
                binding.etMail.setText(usuario.getMail());
                binding.etPassword.setText(usuario.getPassword());
            }
        });
        Intent intent = getIntent();
        int i = (int)intent.getIntExtra("flag", 0);
        if( i == 1){
            mv.LeerUsuario();
        }
        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.GuardarUsuario(
                        binding.etDni.getText().toString(),
                        binding.etApellido.getText().toString(),
                        binding.etNombre.getText().toString(),
                        binding.etMail.getText().toString(),
                        binding.etPassword.getText().toString());
            }
        });
    }
}