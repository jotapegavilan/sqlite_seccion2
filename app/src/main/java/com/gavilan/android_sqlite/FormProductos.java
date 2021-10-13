package com.gavilan.android_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gavilan.android_sqlite.models.Producto;
import com.gavilan.android_sqlite.sqlite.DbProductos;

import java.util.ArrayList;

public class FormProductos extends AppCompatActivity {

    EditText txtNombreProd, txtMarcaProd, txtModeloProd, txtPrecioProd, txtStockProd;
    Button btnProd;
    Spinner spProductos;

    public void cargarSpinner(){
        DbProductos bd = new DbProductos(this);
        ArrayList<Producto> productos = bd.obtenerProductos();
        if( productos != null ){
            ArrayAdapter<Producto> adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_spinner_dropdown_item,
                    productos
            );
            spProductos.setAdapter(adapter);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_productos);
        txtNombreProd = findViewById(R.id.txtNombreProd);
        txtMarcaProd = findViewById(R.id.txtMarcaProd);
        txtModeloProd = findViewById(R.id.txtModeloProd);
        txtPrecioProd = findViewById(R.id.txtPrecioProd);
        txtStockProd = findViewById(R.id.txtStockProd);
        btnProd = findViewById(R.id.btnProd);
        spProductos = findViewById(R.id.spProductos);
        cargarSpinner();
        btnProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombreProd.getText().toString();
                String marca = txtMarcaProd.getText().toString();
                String modelo = txtModeloProd.getText().toString();
                int precio = Integer.parseInt(txtPrecioProd.getText().toString());
                int stock = Integer.parseInt(txtStockProd.getText().toString());
                DbProductos bd = new DbProductos(FormProductos.this);
                long id = bd.insertarProducto(nombre,marca,modelo,precio,stock);
                if( id > 0 ){
                    Toast.makeText(FormProductos.this,
                            "Producto registrado!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(FormProductos.this,
                            "Error al insertar!", Toast.LENGTH_LONG).show();
                }
                cargarSpinner();
            }
        });

    }
}