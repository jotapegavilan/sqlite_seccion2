package com.gavilan.android_sqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.gavilan.android_sqlite.models.Compra;

public class DbCompras extends DbHelper {
    // Contenxt contexto;
    public DbCompras(@Nullable Context context) {
        super(context);
        this.contexto = context;
    }

    public long insertar(Compra compra){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fecha",compra.getFecha());
        values.put("pagado",compra.getPagado());
        values.put("usuario",compra.getUsuario().getId());

        return db.insert(DB_TABLE_COMPRAS, null,
                 values);
    }

}
