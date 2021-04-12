package com.proyecto.ebabotndepnico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_datos_socio.*

class datos_socio : AppCompatActivity() {

    private val bd = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_socio)

        title = "Configuración Aplicativo 1/4"

        btnContactos.setOnClickListener {
            sig()
        }

        val bundle = intent.extras
        val correo = bundle?.getString("correo")
        registro(correo.toString())


    }

    private fun registro(correo:String) {

        btnAct.setOnClickListener {
            if (etNombre.text.isNotEmpty() && etEdad.text.isNotEmpty() && etGenero.text.isNotEmpty()) {
                bd.collection("usuarios").document(correo).set(
                    hashMapOf("nombre" to etNombre.text.toString(),
                        "edad" to etEdad.text.toString(),
                        "genero" to etGenero.text.toString()
                    )

                    )

                msjAceptado()
            }

            else {

                msjError()

            }

        }
    }

    private fun msjError(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Uno o todos los campos estan vacios")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()

    }

    private fun msjAceptado(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Datos Registrados")
        builder.setMessage("Se han registrado tus datos personales")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()

    }

    private fun sig(){
        val contactos = Intent(this, Tratamiento_Datos::class.java)
        startActivity(contactos)
    }


}

