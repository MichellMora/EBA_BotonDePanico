package com.proyecto.ebabotndepnico

import Boton_de_panico.btn_edit
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_datos_socio.*

class datos_socio : AppCompatActivity() {

    private val bd = FirebaseFirestore.getInstance()

    lateinit var opciones:Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_socio)

        title = "Configuración Aplicativo 1/4"

        val bundle = intent.extras
        val correo = bundle?.getString("correo")
        val ID = bundle?.getString("ID")
        registro(correo.toString(),ID.toString())


        val lista = listOf("Femenino", "Maculino", "Otro")
        opciones = findViewById(R.id.etGenero)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        opciones.adapter = adaptador



    }

    private fun registro(correo:String, ID: String) {

        btnAct.setOnClickListener {
            if (etNombre.text.isNotEmpty() && etEdad.text.isNotEmpty() && etGenero.isClickable) {
                //var ID = bd.collection("usuarios").document().id
                bd.collection("usuarios").document(ID).set(
                    hashMapOf("nombre" to etNombre.text.toString(),
                        "edad" to etEdad.text.toString(),
                        "genero" to  opciones.selectedItem.toString(),
                            "correo" to correo
                    )

                    )
                msjAceptado()

                btnBotonEdit.setOnClickListener {
                    configuracion(correo,ID)
                    Log.d("Correo", correo)
                }

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


    private fun configuracion(correo:String, ID:String) {
        val registro = Intent(this, btn_edit()::class.java).apply {
            putExtra("correo", correo)
            putExtra("ID",ID)
        }

        startActivity(registro)
    }
}

