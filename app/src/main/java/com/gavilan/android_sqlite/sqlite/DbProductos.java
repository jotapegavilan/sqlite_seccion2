package com.gavilan.android_sqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.gavilan.android_sqlite.models.Categoria;
import com.gavilan.android_sqlite.models.Producto;

import java.util.ArrayList;

public class DbProductos extends DbHelper {
    Context context;
    public DbProductos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarProducto(Producto prod){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase bd = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", prod.getNombre() );
        values.put("marca", prod.getMarca());
        values.put("modelo", prod.getModelo());
        values.put("precio", prod.getPrecio());
        values.put("stock", prod.getStock());
        values.put("categoria", prod.getCategoria().getId() );
        return bd.insert(DB_TABLE_PRODUCTS, null, values);
    }

    public long insertarProducto(String nombre, String marca, String modelo,
                                 int precio, int stock, int idCategoria){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase bd = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre );
        values.put("marca", marca);
        values.put("modelo", modelo);
        values.put("precio", precio);
        values.put("stock", stock);
        values.put("categoria", idCategoria);
        return bd.insert(DB_TABLE_PRODUCTS, null, values);
    }

    public ArrayList<Producto> obtenerProductos(){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase bd = helper.getReadableDatabase();
        ArrayList<Producto> misProductos = new ArrayList<>();
        Producto producto;
        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM "+DB_TABLE_PRODUCTS,
                null);
        DbCategorias dbcat = new DbCategorias(context);
        if( cursor.moveToFirst() ){
            do{
                Categoria cat =  dbcat.obtenerCategoriaPorId(cursor.getInt(6));
                // SACAR LOS REGISTROS, PONERLOS EN OBJETO Y LUEGO
                // GUARDARLOS EN EL ARRAYLIST QUE SE RETORNARÁ
                producto = new Producto();
                producto.setId( cursor.getInt(0) );
                producto.setNombre( cursor.getString(1) );
                producto.setMarca( cursor.getString(2) );
                producto.setModelo( cursor.getString(3) );
                producto.setPrecio( cursor.getInt(4) );
                producto.setStock( cursor.getInt(5) );
                producto.setCategoria( cat );
                misProductos.add(producto);
            }while (cursor.moveToNext());
            return misProductos;
        }else{
            return null;
        }

    }

}
