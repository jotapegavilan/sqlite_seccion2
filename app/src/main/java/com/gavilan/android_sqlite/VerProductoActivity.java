package com.gavilan.android_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gavilan.android_sqlite.models.Compra;
import com.gavilan.android_sqlite.models.Producto;
import com.gavilan.android_sqlite.models.Usuario;
import com.gavilan.android_sqlite.sqlite.DbCompras;
import com.gavilan.android_sqlite.sqlite.DbProductoEnCompra;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class VerProductoActivity extends AppCompatActivity {

    TextView txt_ver_producto, txt_ver_precio, txt_ver_stock;
    Spinner sp_ver_cantidad;
    Button btn_agregar_al_carro;
    Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_producto);

        txt_ver_producto = findViewById(R.id.txt_ver_producto);
        txt_ver_precio = findViewById(R.id.txt_ver_precio);
        txt_ver_stock = findViewById(R.id.txt_ver_stock);
        sp_ver_cantidad = findViewById(R.id.sp_ver_cantidad);
        btn_agregar_al_carro = findViewById(R.id.btn_agregar_al_carro);

        // Recoger el producto a mostrar
        Intent intent = getIntent();
        producto = (Producto) intent.getExtras().get("producto");

        txt_ver_producto.setText(producto.getNombre()+" "
                +producto.getMarca()+" - "+producto.getModelo());
        txt_ver_precio.setText("$"+producto.getPrecio());
        txt_ver_stock.setText("Quedan "+producto.getStock()+" unidades");

        cargarSpinner(producto.getStock());

        btn_agregar_al_carro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // verificar si hay compra activa
                DbCompras db = new DbCompras(getApplicationContext());
                DbProductoEnCompra dbpc = new DbProductoEnCompra(getApplicationContext());
                int cantidad = Integer.parseInt(
                        sp_ver_cantidad.getSelectedItem().toString() );
                int valor =  db.compraActiva();
                if( valor == -1 ){ // no hay compra activa
                    String fecha_actual =
                            LocalDate.now().getDayOfMonth()+"/"
                                    +LocalDate.now().getMonthValue()+"/"
                                    +LocalDate.now().getYear(); // 26/10/2021
                    Usuario usuario_actual = LoginActivity.usuario_logeado;
                    Compra compra = new Compra(fecha_actual,usuario_actual);
                    long idCompra =  db.insertar(compra);  // compra nueva
                    // agregar los productos
                    dbpc.agregarProductos(producto, idCompra, cantidad);
                    Toast.makeText(VerProductoActivity.this, "Carrito creado",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(VerProductoActivity.this, "Compra activa",
                            Toast.LENGTH_SHORT).show();
                    dbpc.agregarProductos(producto, valor, cantidad);
                }


            }
        });

    }

    public void cargarSpinner(int max){
        ArrayList<Integer> cantidad = new ArrayList<>();

        for( int i=1; i<=max; i++ ){
            cantidad.add(i);
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, cantidad);
        sp_ver_cantidad.setAdapter(adapter);
    }
}