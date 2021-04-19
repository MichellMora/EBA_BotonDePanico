package com.proyecto.ebabotndepnico

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_pag__principal.*
import kotlinx.android.synthetic.main.activity_contactos.*

class Pag_Principal : AppCompatActivity() {

    private val bd = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pag__principal)

        val bundle = intent.extras
        val correo = bundle?.getString("correo")
        //val contactos = bundle?.getCharSequence("listaCont")
        TraerDatos(correo.toString())
        //tvDatos.setText(nombre)


    }

    private fun TraerDatos(correo:String){

        bd.collection("usuarios").document(correo)
            .get().addOnSuccessListener {
            //tvDatos.setText(it.get("nombre") as String?)

                bd.collection("usuarios").document(correo)
                    .collection("contactos").document(etNombreCont.getText().toString())
                    .get().addOnSuccessListener {
                        tvDatos.setText(it.get("Telefono") as String?)
                    }
        }

    }


}