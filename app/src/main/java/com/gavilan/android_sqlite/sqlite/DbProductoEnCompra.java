package com.gavilan.android_sqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
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

}
