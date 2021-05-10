package Boton_de_panico

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.proyecto.ebabotndepnico.R
import kotlinx.android.synthetic.main.activity_contactos.*
import kotlinx.android.synthetic.main.activity_pag__principal.*
import java.net.IDN


class Pag_Principal : AppCompatActivity() {

    private val bd = FirebaseFirestore.getInstance()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pag__principal)

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.SEND_SMS),111)
        }

        enviarSMS()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==111 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

            msjAprobado()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun enviarSMS (/*telefono1 : String, telefono2:String, telefono3:String,telefono4:String*/) {

        val bundle = intent.extras
        val ID = bundle?.getString("ID")
        val correo = bundle?.getString("correo")
        val IDbtn = bd.collection("usuarios").document(ID.toString()).collection("botones").document("jRHaBP85jYEEPZjHhlEP").id

        val IDcont1 = bd.collection("usuarios").document(ID.toString())
                .collection("botones").document(IDbtn)
                .collection("contactos").document("gnAxPhqOWTeh5TxAx9z3").id

        val IDcont2 = bd.collection("usuarios").document(ID.toString())
                .collection("botones").document(IDbtn)
                .collection("contactos").document("IqwbHuRrXp4dHkJEme3j").id

        bd.collection("usuarios").document(ID.toString()).collection("botones").document(IDbtn).get().addOnSuccessListener {

            tvDatos4.setText(it.get("Nombre") as String?)
            tvDatos5.setText(it.get("Mensaje") as String?)

            val MSJ1 = it.get("Mensaje")

            bd.collection("usuarios").document(ID.toString())
                    .collection("botones").document(IDbtn)
                    .collection("contactos").document(IDcont1).get().addOnSuccessListener { document ->

                        if (document.exists()) {

                            var contacto1 = document.getString("Telefono")

                            /*bd.collection("usuarios").document(ID.toString())
                                    .collection("botones").document(IDbtn)
                                    .collection("contactos").document(IDcont2).get().addOnSuccessListener { document ->

                                        if (document.exists()) {

                                            var contacto2 = document.getString("Telefono")*/

                            btnAcoso.setOnClickListener {

                                var cont1 = SmsManager.getDefault()
                                cont1.sendTextMessage(contacto1.toString(),
                                        null, MSJ1.toString(), null, null)

                                /*var cont2 = SmsManager.getDefault()
                                cont2.sendTextMessage(contacto2.toString(),
                                        null, MSJ1.toString(), null, null)*/
                            //}


                       // }
                    }

                            /* btnAcoso.setOnClickListener {

                var cont1 = SmsManager.getDefault()
                cont1.sendTextMessage(telefono1,
                    null, MSJ1.toString() ,null,null)

                var cont2 = SmsManager.getDefault()
                cont2.sendTextMessage(telefono2,
                        null, MSJ1.toString() ,null,null)

               /* var cont3 = SmsManager.getDefault()
                cont3.sendTextMessage(telefono3,
                        null, MSJ1.toString() ,null,null)

                var cont4 = SmsManager.getDefault()
                cont4.sendTextMessage(telefono4,
                        null, MSJ1.toString() ,null,null)*/
            }*/

                        }

                        bd.collection("usuarios").document(ID.toString()).collection("contactos").get().addOnSuccessListener { resultado ->
                            for (documents in resultado) {

                                Log.d("Datos doc", "$documents.id ${documents.data}") // Mostrar datos en logcats

                            }
                        }

                        tvDatos.setText(ID)
                        tvDatos2.setText(correo)
                        tvDatos3.setText(IDbtn)

                    }
        }
    }

    private fun msjAprobado(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Aprobado")
        builder.setMessage("Haz permitido el envío de mensajes por parte de la app EBA")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

   /* @RequiresApi(Build.VERSION_CODES.R)
    private fun obtenerContactos(){

        val bundle = intent.extras
        val ID = bundle?.getString("ID")
        val correo = bundle?.getString("correo")
        val IDbtn =  bd.collection("usuarios").document(ID.toString()).collection("botones").document("jRHaBP85jYEEPZjHhlEP").id



       val IDcont1 = bd.collection("usuarios").document(ID.toString())
                .collection("botones").document(IDbtn)
                .collection("contactos").document("gnAxPhqOWTeh5TxAx9z3").id

        val IDcont2 = bd.collection("usuarios").document(ID.toString())
                .collection("botones").document(IDbtn)
                .collection("contactos").document("IqwbHuRrXp4dHkJEme3j").id

        val IDcont3 = bd.collection("usuarios").document(ID.toString())
                .collection("botones").document(IDbtn)
                .collection("contactos").document("f9CybwrIogVICsrYNVAO").id

        val IDcont4 = bd.collection("usuarios").document(ID.toString())
                .collection("botones").document(IDbtn)
                .collection("contactos").document("NYLVhTBV7mTYuUwkcsRE").id

        bd.collection("usuarios").document(ID.toString())
            .collection("botones").document(IDbtn)
            .collection("contactos").document(IDcont1).get().addOnSuccessListener {document ->

                    if (document.exists()){

                       var contacto1 = document.getString("Telefono")

                        /*bd.collection("usuarios").document(ID.toString())
                                .collection("botones").document(IDbtn)
                                .collection("contactos").document(IDcont2).get().addOnSuccessListener {document ->

                                    if (document.exists()){

                                        var contacto2 = document.getString("Telefono")*/
                                        enviarSMS(contacto1.toString(),"000")
                                    //}
                                //}
                    }
                }

    }

    private fun ubicacion(){
        //geo:<lat>,<lon>?z=<zoom>&q=<lat>,<lon>(<label>)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:4.683963,-74.1499?z=16&q=4.683963,-74.1499(Michell)"))
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.Pag_Principal")
            startActivity(intent)

    }*/


}

