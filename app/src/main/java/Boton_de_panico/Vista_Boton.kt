package Boton_de_panico

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.LightingColorFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import bolts.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.proyecto.ebabotndepnico.R
import kotlinx.android.synthetic.main.activity_vista__boton.*

class Vista_Boton : AppCompatActivity() {

    val canal = "canalID"
    val canalNom = "canalNom"
    val notID = 0
    private val bd = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista__boton)

        btnInicio.setOnClickListener {
            //Boton con Widget
        }

        btnNot.setOnClickListener {
            crearCanal()
            crearNotificacion()

        }
    }

    private fun crearNotificacion() {

        val bundle = intent.extras
        val ID = bundle?.getString("ID")

        bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("jRHaBP85jYEEPZjHhlEP")
            .get().addOnSuccessListener { documento ->


                        var msj = documento.getString("Mensaje")
                        var Nombre = documento.getString("Nombre")

                        val intent = Intent(this, Pag_Principal::class.java).apply {
                            putExtra("ID", ID)
                        }
                        val pendingIntent:PendingIntent? = TaskStackBuilder.create(this).run {

                            addNextIntentWithParentStack(intent)
                            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
                        }

                        val bi = Intent(this,Pag_Principal::class.java).apply {
                            putExtra("ID", ID)
                        }
                        bi.putExtra("EXTRA_ARG", "Sí")
                        val buttonPendingIntent = PendingIntent.getActivity(
                                this,1,bi, PendingIntent.FLAG_ONE_SHOT
                        )

                        val action = NotificationCompat.Action.Builder(
                                R.drawable.logo,
                                Nombre,
                                buttonPendingIntent
                        ).build()

                        val nm = NotificationCompat.Builder(this, canal).also {

                            it.setContentTitle("Notificación")
                            it.setContentText(msj)
                            it.setSmallIcon(R.drawable.logo)
                            it.setPriority(NotificationCompat.PRIORITY_HIGH)
                            it.setContentIntent(pendingIntent)
                            it.addAction(action)
                            it.setAutoCancel(false)

                        }.build()

                        val notificationManager = NotificationManagerCompat.from(this)

                        notificationManager.notify(notID,nm)
            }


    }

    private fun crearCanal() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val importancia = NotificationManager.IMPORTANCE_HIGH
            val canal = NotificationChannel(canal, canalNom, importancia).apply {

                enableLights(true)
            }

            val manager:NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            manager.createNotificationChannel(canal)


        }

    }

    private fun SMS(){

            Log.d("SMS", "Si llega aqui")

    }
}