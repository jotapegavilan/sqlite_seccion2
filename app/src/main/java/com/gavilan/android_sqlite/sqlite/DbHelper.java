package com.gavilan.android_sqlite.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    protected static final String DB_NAME = "tiendAndroid";
    protected static final int DB_VERSION = 5;

    protected Context contexto;

    protected static final String DB_TABLE_USERS = "usuarios";
    protected static final String DB_TABLE_CATEGORIES = "categorias";
    protected static final String DB_TABLE_PRODUCTS = "productos";
    protected static final String DB_TABLE_COMPRAS = "compras";
    protected static final String DB_TABLE_PRODUCTO_COMPRA = "producto_en_compra";


    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+DB_TABLE_USERS+"("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombres TEXT NOT NULL,"+
                "apellidos TEXT NOT NULL,"+
                "email TEXT NOT NULL,"+
                "clave TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE "+DB_TABLE_CATEGORIES+"("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE "+DB_TABLE_PRODUCTS+"("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT NOT NULL, "+
                "marca TEXT NOT NULL,"+
                "modelo TEXT NOT NULL,"+
                "precio INTEGER NOT NULL,"+
                "stock INTEGER NOT NULL,"+
                "categoria INTEGER NOT NULL,"+
                "FOREIGN KEY (categoria) REFERENCES categorias(id))");

        sqLiteDatabase.execSQL("CREATE TABLE "+DB_TABLE_COMPRAS+"("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "fecha TEXT NOT NULL,"+
                "usuario INTEGER NOT NULL,"+
                "FOREIGN KEY (usuario) REFERENCES usuarios(id))");

        sqLiteDatabase.execSQL("CREATE TABLE "+DB_TABLE_PRODUCTO_COMPRA+"("+
                "compra INTEGER NOT NULL,"+
                "producto INTEGER NOT NULL,"+
                "cantidad INTEGER NOT NULL,"+
                "FOREIGN KEY (compra) REFERENCES compras(id),"+
                "FOREIGN KEY (producto) REFERENCES productos(id))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_CATEGORIES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_PRODUCTS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_COMPRAS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_PRODUCTO_COMPRA);
        onCreate(sqLiteDatabase);
    }
}
