package com.proyecto.ebabotndepnico

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_autenticar.*

class Autenticar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autenticar)

        val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integración con Firebase completa")
        analytics.logEvent("InitScreen", bundle)

        conf();
    }

    private fun conf() {
        title = "Bienvenido"

        btnReg.setOnClickListener {
            if (etCorreo.text.isNotEmpty() && etPass.text.isNotEmpty()) {


                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        etCorreo.text.toString(),
                        etPass.text.toString()
                    ).addOnCompleteListener {

                        if (it.isSuccessful) {
                            //val ID = FirebaseAuth.getInstance().currentUser
                            val ID = FirebaseAuth.getInstance().uid
                            enviarCorreo(it.result?.user?.email?:"", ID.toString())
                        } else {

                            registroAlertaError()
                        }
                    }
            }

        }

       btnIng.setOnClickListener {
            if (etCorreo.text.isNotEmpty() && etPass.text.isNotEmpty()) {

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        etCorreo.text.toString(),
                        etPass.text.toString()
                    ).addOnCompleteListener {

                        if (it.isSuccessful) {
                            val ID = FirebaseAuth.getInstance().uid
                            panPrincipal(it.result?.user?.email?:"",ID.toString())

                        } else {

                            mostrarAlerta()
                        }
                    }
            }

        }

    }

    private fun mostrarAlerta() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("No se ha podido Iniciar Sesión")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }


    private fun registroAlertaError() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error en Registro")
        builder.setMessage("Registro NO Exitoso")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /*private fun mostrarDatos(correo: String, provider: ProviderType) {

        val mostrarDatos = Intent(this, Mostrar_Datos::class.java).apply {
            putExtra("correo", correo)
            putExtra("provider", provider.name)
        }

        startActivity(mostrarDatos)
    }*/


    private fun enviarCorreo(correo:String, ID: String) {
        val registro = Intent(this, datos_socio()::class.java).apply {
            putExtra("correo", correo)
            putExtra("ID", ID)
        }

        startActivity(registro)
    }

    private fun panPrincipal(correo:String, ID: String) {
        val registro = Intent(this, btn_edit()::class.java).apply {
            putExtra("correo", correo)
            putExtra("ID", ID)
        }

        startActivity(registro)
    }
}