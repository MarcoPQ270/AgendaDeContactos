package com.example.listasql

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main_activityregistro.*
import kotlinx.android.synthetic.main.content_main_activityregistro.*

class MainActivityregistro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activityregistro)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            if(etCorreoR.text.isEmpty() || etNombreR.text.isEmpty() || etPwdR.text.isEmpty() || etTelefono.text.isEmpty()){
                Toast.makeText(this, "DATOS INCOMPLETOS", Toast.LENGTH_SHORT).show();
                etCorreoR.requestFocus()
            }
            else{
                val ema = etCorreoR.text.toString()
                val nom = etNombreR.text.toString()
                val pwd = etPwdR.text.toString()
                val tel = etTelefono.text.toString()
                val sentencia = "INSERT INTO Usuario( emailUsr,nomUsr,contUsr,telUsr) VALUES('$ema','$nom','$pwd','$tel')"
                val admin = adminBD(this )
                if (admin.Ejecuta(sentencia)==1){
                    Toast.makeText(this, "Usuario Agregado con Exito", Toast.LENGTH_SHORT).show();
                    val lista = Intent (this,MainActivity::class.java)
                    lista.putExtra(MainActivity.EXTRA_CORREO,ema)
                    lista.putExtra(MainActivity.EXTRA_PWD,pwd)
                    startActivity(lista)
                }else{
                    Toast.makeText(this, "ERROR: No se pudo registrar", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
