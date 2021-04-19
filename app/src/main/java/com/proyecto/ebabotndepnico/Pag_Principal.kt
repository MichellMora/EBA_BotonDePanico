package com.proyecto.ebabotndepnico

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_pag__principal.*

class Pag_Principal : AppCompatActivity() {

    private val bd = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pag__principal)

        val bundle = intent.extras
        val correo = bundle?.getString("correo")
        TraerDatos(correo.toString())


    }

   private fun TraerDatos(correo:String){

        bd.collection("usuarios").document(correo)
            .get().addOnSuccessListener {
            tvDatos.setText(it.get("nombre") as String? + it.get("edad") as String?)

        }

      /* bd.collection("usuarios").document(correo)
               .collection("contactos").document(nombre)
               .get().addOnSuccessListener {
                   tvDatos.setText(it.get("nombre") as String? + it.get("edad") as String?)*/
    }


}