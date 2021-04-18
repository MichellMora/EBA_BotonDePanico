package com.proyecto.ebabotndepnico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_tratamiento__datos.*

class Tratamiento_Datos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tratamiento__datos)

        btncont.visibility = View.INVISIBLE

        autorizar()

        cont()

    }

    private fun autorizar() {

        btnAutorizar.setOnClickListener{
            if (btnAutorizar.isChecked == true){

                btncont.visibility = View.VISIBLE

            }

            else
                btncont.visibility = View.INVISIBLE
        }

    }

    private fun cont(){

        btncont.setOnClickListener{

            val reg_contactos = Intent(this, contactos::class.java)
            startActivity(reg_contactos)

        }
    }

}