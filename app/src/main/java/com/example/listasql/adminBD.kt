package com.example.listasql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.Cursor

class adminBD (context: Context): SQLiteOpenHelper(context, DATABASE, null, 1)
{
    companion object{
        val DATABASE="Lista"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE Usuario(" +
                    "emailUsr text primary key," +
                    " nomUsr text ," +
                    " contUsr text," +
                    "telUsr text)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    fun Ejecuta(sentencia : String):Int{
        try {
            val db = this.writableDatabase// se abre la base de datos en modo de lectura y escritura
            db.execSQL(sentencia)
            db.close()
            return 1 //regresa 1 si la sentencia es exitosa
        }catch (ex:Exception){
            return 0
        }
    }

    fun Consulta(select :String): Cursor?{
        try {
            val db = this.readableDatabase // abre solo en modo lectura no permite escribir
            return db.rawQuery(select,null)
        }catch(ex:Exception) {
            return null
        }
    }
}