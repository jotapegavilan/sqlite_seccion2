package com.gavilan.android_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gavilan.android_sqlite.models.Usuario;
import com.gavilan.android_sqlite.sqlite.DbHelper;
import com.gavilan.android_sqlite.sqlite.DbUsuarios;

public class MainActivity extends AppCompatActivity {
    // Definir elementos de la UI
    EditText txtNombres, txtApellidos,txtEmail,txtClave;
    Button btnRegistrar;
    Spinner spUsuarios;

    public void cargarSpinner(){
        DbUsuarios db = new DbUsuarios(this);
        ArrayAdapter<Usuario> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,
                db.selectAll() );
        spUsuarios.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Referencias
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtEmail = findViewById(R.id.txtEmail);
        txtClave = findViewById(R.id.txtClave);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        spUsuarios = findViewById(R.id.spUsuarios);

        cargarSpinner();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // OBTENER TEXTOS INGRESADOS EN LA UI
                String nombres = txtNombres.getText().toString();
                String apellidos = txtApellidos.getText().toString();
                String email = txtEmail.getText().toString();
                String clave = txtClave.getText().toString();
                Usuario user =
                        new Usuario(nombres,apellidos,email,clave);

                DbUsuarios dbUsuarios = new DbUsuarios(MainActivity.this);
                long id =  dbUsuarios.insertar(user);
                Toast.makeText(MainActivity.this,
                        "id:"+id, Toast.LENGTH_SHORT).show();
                cargarSpinner();            }
        });

    }
}