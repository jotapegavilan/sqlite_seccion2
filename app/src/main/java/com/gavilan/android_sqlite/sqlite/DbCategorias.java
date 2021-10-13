package com.gavilan.android_sqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.gavilan.android_sqlite.models.Categoria;

import java.util.ArrayList;

public class DbCategorias extends DbHelper {
    Context context;
    public DbCategorias(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarCategoria(String nom){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase bd = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nom );

        return bd.insert(DB_TABLE_CATEGORIES,
                null,values); // id del registro

    }

    public ArrayList<Categoria> obtenerTodas(){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase bd = helper.getReadableDatabase();
        ArrayList<Categoria> arrayCategorias = new ArrayList<>();
        Categoria catDesdeBD;
        Cursor cursor;

        cursor = bd.rawQuery("SELECT * FROM "+DB_TABLE_CATEGORIES,null);

        if( cursor.moveToFirst() ){ // hay registros
             do{
                 catDesdeBD = new Categoria();
                 catDesdeBD.setId( cursor.getInt(0) );
                 catDesdeBD.setNombre( cursor.getString(1) );
                 arrayCategorias.add(catDesdeBD); // llenando array con reg desde bd

             }while (cursor.moveToNext());
             return arrayCategorias; // una vez lleno lo retornamos

        }else{ // no hay registros
            return null;
        }


    }

}
