package com.proyecto.ebabotndepnico

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_pag__principal.*

class Pag_Principal : AppCompatActivity() {

    private val bd = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pag__principal)

        val bundle = intent.extras
        val ID = bundle?.getString("ID")
        TraerDatos(ID.toString())


    }

   private fun TraerDatos(ID: String) {

       bd.collection("usuarios").document(ID).collection("contactos").
       get().addOnSuccessListener{ resultado->
           for (documents in resultado){

               Log.d("Datos doc","$documents.id ${documents.data}") // Mostrar datos en logcats

           }
       }

       bd.collection("usuarios").document(ID).collection("mensaje").
       get().addOnSuccessListener{ resultado->
           for (documents in resultado){

               Log.d("Datos doc","$documents.id ${documents.data}") // Mostrar datos en logcats
               tvDatos.setText(documents.data.toString())
               //List l = new List <>
           }

       }

       bd.collection("usuarios").document(ID).collection("contactos").
       get().addOnSuccessListener{ resultado->
           for (documents in resultado){

               Log.d("Datos doc","$documents.id ${documents.data}") // Mostrar datos en logcats

           }

       }

   }

    /*bd.collection("usuarios").document(ID)
               .get().addOnSuccessListener { documento ->
                   if(documento.exists()) {
                       val nombre = documento.getString("nombre")
                       val edad = documento.getString("edad")?.toInt()

                       Log.d("Datos doc: ", "$nombre $edad" + ID)// aGrEGAr LO DEMAS DEL ID (CONTACTO, MSJ)
                   }else{
                       //no existe
                   }
               }

       bd.collection("usuarios").document(ID)
               .collection("mensaje").document(IDmsj)
               .get().addOnSuccessListener {
                   tvDatos2.setText(it.get("mensaje") as String? )
               }*/
}