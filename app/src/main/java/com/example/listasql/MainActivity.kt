
package com.example.listasql

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var correo: String=""
    var pws: String=""

    companion object{
        val EXTRA_CORREO="extra_correo"
        val EXTRA_PWD="extra_pwd"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val recibe = intent
        if (recibe != null && recibe.hasExtra(EXTRA_CORREO) && recibe.hasExtra(EXTRA_PWD)){

            recibe.getStringExtra(EXTRA_CORREO)
            recibe.getStringExtra(EXTRA_PWD)
            Toast.makeText(this, "DATOS RECIBIDOS $correo", Toast.LENGTH_SHORT).show();

        }else {
            val admin = adminBD(this)
            val tupla = admin.Consulta("SELECT emailUsr, contUsr FROM Usuario")
            if (tupla!!.moveToFirst()) {
                correo = tupla.getString((0))
                pws = tupla.getString((1))
                Toast.makeText(this, "OK: $correo", Toast.LENGTH_SHORT).show();
            } else {
                val registro = Intent(this, MainActivityregistro::class.java)
                startActivity(registro)
            }
        }
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
