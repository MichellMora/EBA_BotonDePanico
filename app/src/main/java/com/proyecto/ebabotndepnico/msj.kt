package com.proyecto.ebabotndepnico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_msj.*

class msj : AppCompatActivity() {

    private val bd = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_msj)

        title = "Configuración Aplicativo 3/4"

        val bundle = intent.extras
        val correo = bundle?.getString("correo")
        //val contacto = bundle?.getCharSequence("contacto1")
        //etMsjPred.setText(contacto)
        mensaje(correo.toString())

        btnSigPrincipal.setOnClickListener{

            sigPrincipal(correo.toString());//,nombre.toString());

        }

    }

    private fun mensaje(correo:String){

        btnAñadirMsj.setOnClickListener{
            if (etMsjPred.text.isNotEmpty()) {
                bd.collection("usuarios").document(correo)
                    .collection("mensaje").document("mensaje")
                    .set(
                    hashMapOf("mensaje" to etMsjPred.text.toString()
                    )

                )

                msjAceptado()

            }
            else
            {
                msjError()
            }
        }

    }

    private fun msjAceptado(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Datos Registrados")
        builder.setMessage("Se han registrado tus datos personales")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()

    }
    private fun msjError(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Uno o todos los campos estan vacios")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()

    }

    private fun sigPrincipal(correo:String){//, nombre:String) {
        val CorreoPrincipal = Intent(this, Pag_Principal::class.java).apply {
            putExtra("correo", correo)
            //putExtra("nombre", nombre)
        }

        startActivity(CorreoPrincipal)
    }
}