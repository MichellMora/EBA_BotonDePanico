package Boton_de_panico

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.proyecto.ebabotndepnico.R
import com.proyecto.ebabotndepnico.contactos
import kotlinx.android.synthetic.main.activity_btn_edit.*


class btn_edit : AppCompatActivity() {

    private val bd = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_btn_edit)


        val bundle = intent.extras
        val ID = bundle?.getString("ID")

        //Crear o motrar datos de botones
        DatosAcoso(ID.toString())
        DatosInfarto(ID.toString())
        DatosRobo(ID.toString())
        DatosAlzheimer(ID.toString())

        val correo = bundle?.getString("correo")
        val IDmsj = bundle?.getString("IDmsj")

        TraerDatos(correo.toString(),
                ID.toString(),
                IDmsj.toString())
    }

    private fun enviarDatosBtn(ID: String, IDbtn: String, msj:String, nomBoton:String){

        btnSigPr.setOnClickListener{

            if(btn1.text.isEmpty() && btn2.text.isEmpty() && btn3.text.isEmpty() && btn4.text.isEmpty() )
                {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Error")
                    builder.setMessage("Debes tener minimo un botón de pánico para continuar")
                    builder.setPositiveButton("Aceptar", null)
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
            else
                {
                    val bundle = intent.extras
                    val correo = bundle?.getString("correo")

                    val Pa_Principal = Intent(this, Pag_Principal::class.java).apply {
                        putExtra("correo", correo)
                        putExtra("ID", ID)
                        putExtra("IDbtn", IDbtn)
                        putExtra("msj", msj)
                        putExtra("nomBoton", nomBoton)
                    }

                    startActivity(Pa_Principal)
                }
        }

    }


    //Crea los botones predeterminados para el usuario

    private fun crearDatos(ID: String, IDbtn :String, nombrebtn: String, msjbtn: String,btn:Button,msj:TextView) {

        btn.setText(nombrebtn)
        msj.setText(msjbtn)

        bd.collection("usuarios").document(ID)
            .collection("botones").document(IDbtn).set(
                hashMapOf("Nombre" to nombrebtn,
                    "Mensaje" to msjbtn
                )
            )
        mostrarDatos(ID,IDbtn,btn,msj) // se inicializa la función despues de hacer el registro automatico de botones predeterminados
        }

    //muestra los datos del botón y mensaje almacenados en la B.D.

    private fun mostrarDatos(ID: String, IDbtn: String,btn: Button,msj: TextView) {

        bd.collection("usuarios").document(ID.toString())
            .collection("botones").document(IDbtn).get().addOnSuccessListener {

                btn.setText(it.get("Nombre") as String?)
                msj.setText(it.get("Mensaje") as String?)
            }

        enviarDatosBtn(ID, IDbtn, msj.getText().toString(),btn.getText().toString())

        btn.setOnClickListener{
            boton(btn,ID,IDbtn,msj) }


    }

    //Creación y Consulta de datos, revisa si ya esta creado el botón o no

    private fun DatosAcoso(ID: String){

        val IDbtn = bd.collection("usuarios").document(ID).
        collection("botones").document("jRHaBP85jYEEPZjHhlEP").id

        bd.collection("usuarios").document(ID.toString())
                .collection("botones").document(IDbtn).get().addOnSuccessListener {

                    if (it.exists()) {
                        mostrarDatos(ID,IDbtn,btn1,msj1) }
                    else {
                        crearDatos(ID,IDbtn,"Acoso", "Me estan acosando, ayuda",btn1,msj1)}

                }

        elm_btn1.setOnClickListener{
            elmbtn(btn1,ID,IDbtn,msj1)
        }

        btnAddCont1.setOnClickListener{
            añadirContacto(ID,IDbtn)
        }

    }
    //Crear y consultar datos infarto

    private fun DatosInfarto(ID: String) {

        val IDbtn =  bd.collection("usuarios").document(ID.toString())
                .collection("botones").document("l2blui94fwoJv55pk3vq").id

        bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("l2blui94fwoJv55pk3vq").get().addOnSuccessListener {

                if (it.exists()) {
                    mostrarDatos(ID,IDbtn,btn2,msj2) }
                else {
                    crearDatos(ID,IDbtn,"Infarto","Me esta dando un infarto, ayuda",btn2,msj2)}
            }

        elm_btn2.setOnClickListener{
            elmbtn(btn2,ID,IDbtn,msj2)
        }

        btnAddCont2.setOnClickListener{
            añadirContacto(ID,IDbtn)
        }
    }

    // Crear y consultar datos robo

    private fun DatosRobo(ID: String){

        val IDbtn = bd.collection("usuarios").document(ID.toString())
                .collection("botones").document("Jqji3ncjefaSHB5qUVnW").id

        bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("Jqji3ncjefaSHB5qUVnW").get().addOnSuccessListener {

                if (it.exists()) {
                    mostrarDatos(ID,IDbtn,btn3,msj3)}
                else {
                    crearDatos(ID,IDbtn,"Robo","Me acaban de robar, esta es la ubicación",btn3,msj3)}
            }

        elm_btn3.setOnClickListener{
            elmbtn(btn3,ID,IDbtn,msj3)
        }

        btnAddCont3.setOnClickListener{
            añadirContacto(ID,IDbtn)
        }
    }

    // Crear y consultar datos alzheimer

    private fun DatosAlzheimer(ID: String){

        val IDbtn = bd.collection("usuarios").document(ID.toString())
                .collection("botones").document("RaaaBnnIUawPjsiKke0k").id

        bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("RaaaBnnIUawPjsiKke0k").get().addOnSuccessListener {

                if (it.exists()) {
                    mostrarDatos(ID,IDbtn,btn4,msj4)}
                else {
                    crearDatos(ID,IDbtn,"Alzheimer", "Mi ubicación actual es esta",btn4,msj4)}
            }

        elm_btn4.setOnClickListener{
            elmbtn(btn4,ID,IDbtn,msj4)
        }

        btnAddCont4.setOnClickListener{
            añadirContacto(ID,IDbtn)
        }
    }

    private fun elmbtn (btn:Button, ID:String, IDbtn:String, msjbtn:TextView){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar Botón")
        builder.setMessage("¿Estas segur@ de eliminar este botón?")
            with(builder){
                setPositiveButton("Borrar Botón"){
                        dialog, which ->

                    bd.collection("usuarios").document(ID)
                        .collection("botones").document(IDbtn)
                        .set(
                            kotlin.collections.hashMapOf(
                                "Nombre" to "",
                                "Mensaje" to ""
                            )

                        )

                    btn.text = ""
                    msjbtn.text = ""

                }

                setNegativeButton("Cancelar"){dialog, which->
                    android.util.Log.d("Main", "Negativo")
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }

        }

    private fun añadirContacto(ID:String, IDbtn:String){

        val registro = Intent(this, contactos::class.java).apply {
            putExtra("IDbtn", IDbtn)
            putExtra("ID", ID)
        }

        startActivity(registro)
    }



    private  fun  boton(btn:Button,ID:String, IDbtn1:String, msjbtn:TextView){


            val builder  =AlertDialog.Builder(this)
            val inflater=layoutInflater
            val confBtn = inflater.inflate(R.layout.activity_popup_edboton, null)
            val etNombtn =confBtn.findViewById<EditText>(R.id.etNombrebtn)
            val etmsj = confBtn.findViewById<EditText>(R.id.etMsjBtn)

            with(builder){
                setPositiveButton("OK"){
                    dialog, which ->

                    if(etNombtn.text.isEmpty() || etmsj.text.isEmpty()){
                        val builder = AlertDialog.Builder(this@btn_edit)
                        builder.setTitle("Error")
                        builder.setMessage("Uno o ambos campos estan vacios")
                        builder.setPositiveButton("Aceptar", null)
                        val dialog: AlertDialog = builder.create()
                        dialog.show()
                    }
                    else{
                        bd.collection("usuarios").document(ID)
                            .collection("botones").document(IDbtn1)
                            .set(
                                hashMapOf(
                                    "Nombre" to etNombtn.text.toString(),
                                    "Mensaje" to etmsj.text.toString()
                                )

                            )

                        btn.text = etNombtn.text.toString()
                        msjbtn.text = etmsj.text.toString()


                    }


                }

                setNegativeButton("Cancelar"){dialog, which->
                    Log.d("Main", "Negativo")
                }
                setView(confBtn)
                show()
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



