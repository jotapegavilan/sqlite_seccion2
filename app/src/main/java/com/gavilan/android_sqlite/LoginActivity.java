package com.gavilan.android_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gavilan.android_sqlite.models.Usuario;
import com.gavilan.android_sqlite.sqlite.DbUsuarios;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnRegister;
    EditText txtEmailLogin, txtClaveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        txtEmailLogin = findViewById(R.id.txtEmailLogin);
        txtClaveLogin = findViewById(R.id.txtClaveLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento =
                        new Intent(LoginActivity.this,MainActivity.class );
                startActivity(intento);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbUsuarios bd = new DbUsuarios(LoginActivity.this);
                // Capturar los datos para pasarlos al método login->DbUsuarios
                String e = txtEmailLogin.getText().toString();
                String c = txtClaveLogin.getText().toString();

                Usuario userLog = bd.login(e,c);

                // puede ser que userLog sea null o no
                if( userLog == null ){ // significa error de credenciales
                    Toast.makeText(LoginActivity.this,
                            "Error en las credenciales ingresadas"
                            , Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this,
                            "Bienvenid@ "+userLog.getNombres()
                            , Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,
                            FormProductos.class);
                    startActivity(intent);
                }

            }
        });

    }
}