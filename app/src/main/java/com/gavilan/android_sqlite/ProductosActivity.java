package com.gavilan.android_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.gavilan.android_sqlite.adapters.ProductoAdapter;
import com.gavilan.android_sqlite.models.Categoria;
import com.gavilan.android_sqlite.sqlite.DbCategorias;
import com.gavilan.android_sqlite.sqlite.DbCompras;
import com.gavilan.android_sqlite.sqlite.DbProductoEnCompra;
import com.gavilan.android_sqlite.sqlite.DbProductos;

import java.util.ArrayList;

public class ProductosActivity extends AppCompatActivity {
    RecyclerView recyclerProductos;
    Spinner spCatFilter;
    Button btnPagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        btnPagar = findViewById(R.id.btnPagarCarro);
        recyclerProductos = findViewById(R.id.recyclerProductos);
        spCatFilter = findViewById(R.id.spCatFilter);
        cargarCat();
        cargarRecycler(null);
        spCatFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Categoria cat= (Categoria) spCatFilter.getSelectedItem();
                if(cat.getId()==0){
                    cargarRecycler(null);
                }else{
                    cargarRecycler(cat);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        DbCompras dbc = new DbCompras(this);
        DbProductoEnCompra dbpc = new DbProductoEnCompra(this);
        int total = dbpc.total(dbc.compraActiva());
        btnPagar.setText("Pagar $"+total);

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbc.finalizarCompra(dbc.compraActiva());
                Toast.makeText(ProductosActivity.this,
                        "Compra pagada", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ProductosActivity.class));
            }
        });
    }

    public void cargarCat(){
        DbCategorias db = new DbCategorias(this);
        ArrayList<Categoria> cats = new ArrayList<>();
        cats.add(new Categoria(0,"Todos los productos"));
        cats.addAll(db.obtenerTodas());
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, cats);
        spCatFilter.setAdapter(adapter);
    }

    public void cargarRecycler(Categoria categoria){
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        DbProductos db = new DbProductos(this);
        ProductoAdapter adapter = null;
        if(categoria == null){
           adapter  = new ProductoAdapter(db.obtenerProductos());
        }else{
            adapter  = new ProductoAdapter(db.obtenerProductos(categoria.getId()));
        }
        recyclerProductos.setAdapter(adapter);
    }
}