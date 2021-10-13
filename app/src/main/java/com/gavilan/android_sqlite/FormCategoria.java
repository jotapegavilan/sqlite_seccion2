package com.gavilan.android_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gavilan.android_sqlite.models.Categoria;
import com.gavilan.android_sqlite.sqlite.DbCategorias;

import java.util.ArrayList;

public class FormCategoria extends AppCompatActivity {

    EditText txtNombreCat;
    Button btnCat;
    Spinner spCategorias;

    public void cargarSpinner(){
        DbCategorias bd = new DbCategorias(this);
        ArrayList<Categoria> misCategorias = bd.obtenerTodas();

        if( misCategorias != null ){ // si hay categorías
            ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_spinner_dropdown_item,
                    misCategorias
            );
            spCategorias.setAdapter(adapter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_categoria);

        txtNombreCat = findViewById(R.id.txtNombreCat);
        btnCat = findViewById(R.id.btnCat);
        spCategorias = findViewById(R.id.spCategorias);

        cargarSpinner();

        btnCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombreCat.getText().toString();
                DbCategorias bd = new DbCategorias(FormCategoria.this);
                long id = bd.insertarCategoria(nombre);
                if( id > 0){
                    Toast.makeText(FormCategoria.this,
                            "Registro exitoso!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(FormCategoria.this,
                            "Error al registrar!", Toast.LENGTH_LONG).show();
                }
                cargarSpinner();
            }
        });



    }
}