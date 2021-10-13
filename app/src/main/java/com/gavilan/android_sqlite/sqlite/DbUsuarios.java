package com.gavilan.android_sqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.gavilan.android_sqlite.models.Usuario;

import java.util.ArrayList;

public class DbUsuarios extends DbHelper {

    DbHelper helper;

    public DbUsuarios(@Nullable Context context) {
        super(context);
        helper = new DbHelper(context);
    }

    public long insertar(Usuario user){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues datos = new ContentValues();
        datos.put("nombres", user.getNombres());
        datos.put("apellidos",user.getApellidos());
        datos.put("email",user.getEmail());
        datos.put("clave",user.getClave());

        long id = db.insert(DB_TABLE_USERS, null, datos);
        return id;
    }

    public ArrayList<Usuario> selectAll(){
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<Usuario> arrayUsuarios = new ArrayList<>();
        Usuario user = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_USERS,
                null);

        if( cursor.moveToFirst() ){
            do{
                user = new Usuario();
                user.setId( cursor.getInt(0) );
                user.setNombres( cursor.getString(1) );
                user.setApellidos( cursor.getString(2) );
                user.setEmail( cursor.getString(3) );
                user.setClave( cursor.getString(4) );
                arrayUsuarios.add(user);
            }while ( cursor.moveToNext() );
        }
        return arrayUsuarios;

    }
}
