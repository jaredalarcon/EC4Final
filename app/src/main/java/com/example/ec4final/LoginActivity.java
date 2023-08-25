package com.example.ec4final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.example.ec4final.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding =ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_login);

        //PRESIONAR BOTON DE LOGIN
        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.txtEmail.getText().toString();
            String password = binding.txtPassword.getText().toString();
            if (email.equals("ejemploe@idat.com.pe") && password.equals("Peru123.")) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "Bienvenido",Toast.LENGTH_SHORT).show();
                //EL LOGIN SE MANTIENE EN SEGUNDO PLANO
                finish();
            }else{
                Toast.makeText(LoginActivity.this, "Error al iniciar sesion",Toast.LENGTH_SHORT).show();
                // Aquí puedes mostrar un mensaje de error o realizar cualquier otra acción en caso de credenciales incorrectas.
            }
        });
        //VALIDAMOS EL CORREO PARA HABILITAR EL BOTON
        binding.txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //SIN CONTENIDO
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString();
                String password = binding.txtPassword.getText().toString();
                boolean isOK = email.equals("ejemploe@idat.com.pe") && password.equals("Peru123.");
                binding.btnLogin.setEnabled(isOK);
                if(isOK){
                    binding.btnLogin.setTextColor(getColor(R.color.white));
                    binding.btnLogin.setBackgroundColor(getColor(R.color.md_theme_dark_onTertiary));
                }else{
                    binding.btnLogin.setTextColor(getColor(R.color.md_theme_dark_surfaceVariant));
                    binding.btnLogin.setBackgroundColor(getColor(R.color.md_theme_dark_background));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                //SIN CONTENIDO
            }
        });
        //VALIDAMOS LA CNOTRASEÑA PARA HABILITAR EL BOTON
        binding.txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //SIN CONTENIDO
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = binding.txtEmail.getText().toString();
                String password = s.toString();
                boolean isOK = email.equals("ejemploe@idat.com.pe") && password.equals("Peru123.");
                binding.btnLogin.setEnabled(isOK);
                if(isOK){
                    binding.btnLogin.setTextColor(getColor(R.color.white));
                    binding.btnLogin.setBackgroundColor(getColor(R.color.md_theme_dark_onTertiary));
                }else{
                    binding.btnLogin.setTextColor(getColor(R.color.md_theme_dark_surfaceVariant));
                    binding.btnLogin.setBackgroundColor(getColor(R.color.md_theme_dark_background));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                //SIN CONTENIDO
            }
        });
    }
}