package Boton_de_panico

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.firestore.FirebaseFirestore
import com.proyecto.ebabotndepnico.R
import com.proyecto.ebabotndepnico.contactos
import kotlinx.android.synthetic.main.activity_btn_edit.*
import kotlinx.android.synthetic.main.activity_pag__principal.*

class Pag_Principal : AppCompatActivity() {

    private val bd = FirebaseFirestore.getInstance()
    private val code =  11
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pag__principal)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        solicitarPermisos()
        enviarSMS()
        //ubicacion()
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

        mostrarDatos(ID.toString(),IDbtn1,btnMSJ1)
        mostrarDatos(ID.toString(),IDbtn2,btnMSJ2)
        mostrarDatos(ID.toString(),IDbtn3,btnMSJ3)
        mostrarDatos(ID.toString(),IDbtn4,btnMSJ4)



    }

    private fun mostrarDatos(ID: String, IDbtn: String, btn: Button) {

        bd.collection("usuarios").document(ID.toString())
                .collection("botones").document(IDbtn).get().addOnSuccessListener {

                    btn.setText(it.get("Nombre") as String?)
                }}


    private fun solicitarPermisos(){
        val SMS = ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
        val Localizador = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

        if(SMS!=PackageManager.PERMISSION_GRANTED && Localizador!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.SEND_SMS,Manifest.permission.ACCESS_FINE_LOCATION),code)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode==code && grantResults[0]==PackageManager.PERMISSION_GRANTED){

            msjAprobado()

        } }

    // SMS

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

        btnMSJ1.setOnClickListener {btnMSJ(ID.toString(),IDbtn1,IDcont1,IDcont2,IDcont3,IDcont4) }

        btnMSJ2.setOnClickListener { btnMSJ(ID.toString(), IDbtn2, IDcont1, IDcont2, IDcont3, IDcont4) }

        btnMSJ3.setOnClickListener{ btnMSJ(ID.toString(),IDbtn3,IDcont1,IDcont2,IDcont3,IDcont4)}

        btnMSJ4.setOnClickListener{ btnMSJ(ID.toString(),IDbtn4,IDcont1,IDcont2,IDcont3,IDcont4)}

    }

    private fun btnMSJ(ID : String, IDbtn: String, IDcont1: String, IDcont2 :String, IDcont3:String, IDcont4:String){

        bd.collection("usuarios").document(ID.toString())
                .collection("botones").document(IDbtn)
                .get().addOnSuccessListener {

                    val MSJ = it.get("Mensaje")
                    msjBoton(ID.toString(),IDbtn,IDcont1,IDcont2,IDcont3,IDcont4,MSJ.toString())
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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val lat = location.latitude
                        val lon = location.longitude
                        Log.d("Ubicacion", "$lat  $lon");

                        bd.collection("usuarios").document(ID)
                                .collection("botones").document(IDbtn)
                                .collection("contactos").document(IDcont).get().addOnSuccessListener { document ->
                                    if (document.exists()) {
                                        if (MSJ.isEmpty()){
                                            Log.d("MSj", "El mensaje esta vacio")
                                        }else{
                                            var contacto = document.getString("Telefono")
                                            var cont = SmsManager.getDefault()
                                            cont.sendTextMessage(contacto.toString(),
                                                    null, MSJ + "https://maps.google.com/maps?q=${lat}${lon}" , null, null)
                                        }

                                    }else {

                                        Log.d("Contacto","Contacto no existe")

                                    }

                                }

                    }else{
                        Log.d("Ubicacion", "No encontrada");
                    }
                }

        /*bd.collection("usuarios").document(ID.toString())
                .collection("botones").document(IDbtn)
                .collection("contactos").document(IDcont).get().addOnSuccessListener { document ->
                    if (document.exists()) {
                        if (MSJ.isEmpty()){
                            Log.d("MSj", "El mensaje esta vacio")
                        }else{
                            var contacto = document.getString("Telefono")
                            var cont = SmsManager.getDefault()
                            cont.sendTextMessage(contacto.toString(),
                                    null, MSJ , null, null)
                        }

                    }else {

                        Log.d("Contacto","Contacto no existe")

                    }

                }*/

    }

    //Ubicación

  /*  private fun ubicacion(){

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val lat = location.latitude
                        val lon = location.longitude
                        Log.d("Ubicacion", "$lat  $lon");

                        btnMSJ1.setOnClickListener {
                            //val mapa = Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/maps?q=${lat} ${lon}"))
                            var cont = SmsManager.getDefault()
                            cont.sendTextMessage("3015935096",
                                    null, "Ubicación actual" + "https://maps.google.com/maps?q=${lat} , ${lon}", null, null)
                            //startActivity(mapa)
                        }


                    }else{
                        Log.d("Ubicacion", "No encontrada");
                    }
                }

    }*/
}
