package com.gavilan.android_sqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.gavilan.android_sqlite.models.Compra;
import com.gavilan.android_sqlite.models.Producto;

public class DbProductoEnCompra extends DbHelper {
    public DbProductoEnCompra(@Nullable Context context) {
        super(context);
    }

    public long agregarProductos(Producto prod, long idCompra, int cantidad){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("compra", idCompra);
        values.put("producto", prod.getId());
        values.put("cantidad",cantidad);

        return db.insert(DB_TABLE_PRODUCTO_COMPRA, null, values);

    }

    public int total(int compra){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT producto, cantidad FROM producto_en_compra  WHERE compra = ?",
                new String[] { String.valueOf(compra) });

        DbProductos dbp = new DbProductos(contexto);

        int total = 0;

        if( cursor.moveToFirst()){
            do{
                 int precio = dbp.obtenerPrecio( cursor.getInt(0) );
                 total+= (precio * cursor.getInt(1));
            }while (cursor.moveToNext());

        }
        return total;

    }

}
