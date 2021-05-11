package Boton_de_panico

import android.Manifest
import android.content.pm.PackageManager
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
import kotlinx.android.synthetic.main.activity_pag__principal.*


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
    private fun enviarSMS () {

        val bundle = intent.extras
        val ID = bundle?.getString("ID")
        val correo = bundle?.getString("correo")

        val IDbtn1 = bd.collection("usuarios")
                .document(ID.toString()).collection("botones")
                .document("jRHaBP85jYEEPZjHhlEP").id

        val IDbtn2 = bd.collection("usuarios")
                .document(ID.toString()).collection("botones")
                .document("l2blui94fwoJv55pk3vq").id

        val IDbtn3 = bd.collection("usuarios")
                .document(ID.toString()).collection("botones")
                .document("Jqji3ncjefaSHB5qUVnW").id

        val IDbtn4 = bd.collection("usuarios")
                .document(ID.toString()).collection("botones")
                .document("RaaaBnnIUawPjsiKke0k").id

        //tvDatos.setText(ID)
        //tvDatos2.setText(correo)
        //tvDatos3.setText(IDbtn1)

        val IDcont1 = bd.collection("usuarios").document(ID.toString())
                .collection("botones").document(IDbtn1)
                .collection("contactos").document("gnAxPhqOWTeh5TxAx9z3").id

        val IDcont2 = bd.collection("usuarios").document(ID.toString())
                .collection("botones").document(IDbtn1)
                .collection("contactos").document("IqwbHuRrXp4dHkJEme3j").id

        val IDcont3 = bd.collection("usuarios").document(ID.toString())
                .collection("botones").document(IDbtn1)
                .collection("contactos").document("f9CybwrIogVICsrYNVAO").id

        val IDcont4 = bd.collection("usuarios").document(ID.toString())
                .collection("botones").document(IDbtn1)
                .collection("contactos").document("NYLVhTBV7mTYuUwkcsRE").id

        btnMSJ1.setOnClickListener {
            bd.collection("usuarios").document(ID.toString())
                    .collection("botones").document(IDbtn1)
                    .get().addOnSuccessListener {

                        val MSJ1 = it.get("Mensaje")
                        msjBoton(ID.toString(),IDbtn1,IDcont1,IDcont2,IDcont3,IDcont4, MSJ1.toString())}



        }

        btnMSJ2.setOnClickListener {
            bd.collection("usuarios").document(ID.toString())
                    .collection("botones").document(IDbtn2)
                    .get().addOnSuccessListener {

                        val MSJ3 = it.get("Mensaje")
                        msjBoton(ID.toString(), IDbtn2, IDcont1, IDcont2, IDcont3, IDcont4, MSJ3.toString())

                    }
        }

        btnMSJ3.setOnClickListener{
            bd.collection("usuarios").document(ID.toString())
                    .collection("botones").document(IDbtn3)
                    .get().addOnSuccessListener {

                        val MSJ3 = it.get("Mensaje")
                        msjBoton(ID.toString(),IDbtn3,IDcont1,IDcont2,IDcont3,IDcont4,MSJ3.toString())

                    }

        }

        btnMSJ4.setOnClickListener{
            bd.collection("usuarios").document(ID.toString())
                    .collection("botones").document(IDbtn4)
                    .get().addOnSuccessListener {

                        val MSJ4 = it.get("Mensaje")
                        msjBoton(ID.toString(),IDbtn4,IDcont1,IDcont2,IDcont3,IDcont4,MSJ4.toString())

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

    private fun msjBoton(ID : String, IDbtn: String, IDcont1: String, IDcont2 :String, IDcont3:String, IDcont4:String, MSJ: String){

        bd.collection("usuarios").document(ID.toString())
                .collection("botones").document(IDbtn)
                .get().addOnSuccessListener {

                    bd.collection("usuarios").document(ID.toString())
                            .collection("botones").document(IDbtn)
                            .collection("contactos").document(IDcont1).get().addOnSuccessListener { document ->

                                if (document.exists()) {

                                        msj(MSJ.toString(), ID.toString(),IDbtn,IDcont1)
                                        msj(MSJ.toString(), ID.toString(),IDbtn,IDcont2)
                                        msj(MSJ.toString(), ID.toString(),IDbtn,IDcont3)
                                        msj(MSJ.toString(), ID.toString(),IDbtn,IDcont4)


                                } else {


                                        msj(MSJ.toString(), ID.toString(),IDbtn,IDcont2)
                                        msj(MSJ.toString(), ID.toString(),IDbtn,IDcont3)
                                        msj(MSJ.toString(), ID.toString(),IDbtn,IDcont4)



                                }

                            }
                }

    }


    private fun msj(MSJ:String, ID:String,IDbtn:String,IDcont:String){

        bd.collection("usuarios").document(ID.toString())
                .collection("botones").document(IDbtn)
                .collection("contactos").document(IDcont).get().addOnSuccessListener { document ->
                    if (document.exists()) {
                        if (MSJ.isEmpty()){
                            Log.d("MSj", "El mensaje esta vacio")
                        }else{
                            var contacto = document.getString("Telefono")
                            var cont = SmsManager.getDefault()
                            cont.sendTextMessage(contacto.toString(),
                                    null, MSJ, null, null)
                        }

                    }else {

                        Log.d("Contacto","Contacto no existe")

                    }

                }

    }





}

