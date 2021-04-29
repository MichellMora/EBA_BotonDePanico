package com.proyecto.ebabotndepnico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_btn_edit.*


class btn_edit : AppCompatActivity() {

    private val bd = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_btn_edit)

        val bundle = intent.extras
        val correo = bundle?.getString("correo")
        val ID = bundle?.getString("ID")
        val IDmsj = bundle?.getString("IDmsj")
        TraerDatos(correo.toString(),
                ID.toString(),
                IDmsj.toString())

        btnAdd.setOnClickListener {
            var popup = PopupWindow(this)
            val view =layoutInflater.inflate(R.layout.add_boton_popup, null)
            popup.contentView = view
            popup.showAtLocation(view, 200, 200, 200)
        }
    }

    private fun TraerDatos(correo: String, ID: String, IDmsj: String) {

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
                //tvDatos.setText(documents.data.toString())
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

}

