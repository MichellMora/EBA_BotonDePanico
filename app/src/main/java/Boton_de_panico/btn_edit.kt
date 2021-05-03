package Boton_de_panico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.proyecto.ebabotndepnico.R
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

    //Creación y Consulta de datos Acoso

    private fun DatosAcoso(ID: String){

        bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("jRHaBP85jYEEPZjHhlEP").get().addOnSuccessListener {

                if (it.exists()) {
                    mostrarDatosbtn1(ID) }
                else {
                    crearDatosAcoso(ID)}
            }

    }
    private fun crearDatosAcoso(ID: String) {
        val nombrebtn = "Acoso"
        val msjbtn = "Ayuda"

        btn1.setText(nombrebtn)
        msj1.setText(msjbtn)

        bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("jRHaBP85jYEEPZjHhlEP").set(
                hashMapOf("Nombre" to nombrebtn,
                    "Mensaje" to msjbtn
                )
            )
        mostrarDatosbtn1(ID) // se inicializa la función despues de hacer el registro automatico de botones predeterminados
        }

    private fun mostrarDatosbtn1(ID: String) {

        val IDbtnAcoso = "jRHaBP85jYEEPZjHhlEP"

        bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("jRHaBP85jYEEPZjHhlEP").get().addOnSuccessListener {

                btn1.setText(it.get("Nombre") as String?)
                msj1.setText(it.get("Mensaje") as String?)
            }

        enviarDatosBtn(ID, IDbtnAcoso, msj1.getText().toString(),btn1.getText().toString())

        val IDbtn1 = bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("jRHaBP85jYEEPZjHhlEP").id

        btn1.setOnClickListener{
            boton(btn1,ID,IDbtn1,msj1) }

        elm_btn1.setOnClickListener{
            elmbtn(btn1,ID,IDbtn1,msj1)
        }
    }

    //Crear y consultar datos infarto

    private fun DatosInfarto(ID: String) {
        bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("l2blui94fwoJv55pk3vq").get().addOnSuccessListener {

                if (it.exists()) {
                    mostrarDatosbtn2(ID) }
                else {
                    crearDatosInfarto(ID)}
            }
    }

    private fun crearDatosInfarto(ID: String) {

        val nombrebtn = "Infarto"
        val msjbtn = "Estoy teniendo un infarto, ayuda"

        btn2.setText(nombrebtn)
        msj2.setText(msjbtn)

        bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("l2blui94fwoJv55pk3vq").set(
                hashMapOf("Nombre" to nombrebtn,
                    "Mensaje" to msjbtn

                )
            )

        mostrarDatosbtn2(ID)
    }

    private fun mostrarDatosbtn2(ID: String) {

        bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("l2blui94fwoJv55pk3vq").get().addOnSuccessListener {

                btn2.setText(it.get("Nombre") as String?)
                msj2.setText(it.get("Mensaje") as String?)
            }

        val IDbtn2 = bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("l2blui94fwoJv55pk3vq").id

        btn2.setOnClickListener{
            boton(btn2,ID,IDbtn2,msj2) }

        elm_btn2.setOnClickListener{
            elmbtn(btn2,ID,IDbtn2,msj2)
        }
    }

    // Crear y consultar datos robo

    private fun DatosRobo(ID: String){

        bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("Jqji3ncjefaSHB5qUVnW").get().addOnSuccessListener {

                if (it.exists()) {
                    mostrarDatosbtn3(ID)}
                else {
                    crearDatosRobo(ID)}
            }
    }

    private fun crearDatosRobo(ID: String) {

        val nombrebtn = "Robo"
        val msjbtn = "Me robaron, ayuda"

        btn3.setText(nombrebtn)
        msj3.setText(msjbtn)

        bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("Jqji3ncjefaSHB5qUVnW").set(
                hashMapOf("Nombre" to nombrebtn,
                    "Mensaje" to msjbtn

                )
            )

        mostrarDatosbtn3(ID)

    }

    private fun mostrarDatosbtn3(ID: String) {

        bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("Jqji3ncjefaSHB5qUVnW").get().addOnSuccessListener {

                btn3.setText(it.get("Nombre") as String?)
                msj3.setText(it.get("Mensaje") as String?)
            }

        val IDbtn3 = bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("Jqji3ncjefaSHB5qUVnW").id

        btn3.setOnClickListener{
            boton(btn3,ID,IDbtn3,msj3) }

        elm_btn3.setOnClickListener{
            elmbtn(btn3,ID,IDbtn3,msj3)
        }
    }

    // Crear y consultar datos alzheimer

    private fun DatosAlzheimer(ID: String){

        bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("RaaaBnnIUawPjsiKke0k").get().addOnSuccessListener {

                if (it.exists()) {
                    mostrarDatosbtn4(ID)}
                else {
                    crearDatosAlzheimer(ID)}
            }

    }

    private fun crearDatosAlzheimer(ID: String) {
        val nombrebtn = "Alzheimer"
        val msjbtn = "Estoy en esta ubicación"

        btn4.setText(nombrebtn)
        msj4.setText(msjbtn)

        bd.collection("usuarios").document(ID)
            .collection("botones").document("RaaaBnnIUawPjsiKke0k").set(
                hashMapOf("Nombre" to nombrebtn,
                    "Mensaje" to msjbtn
                )
            )

        mostrarDatosbtn4(ID)
    }


    //Alzheimer
    private fun mostrarDatosbtn4(ID: String) {

        bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("RaaaBnnIUawPjsiKke0k").get().addOnSuccessListener {

                btn4.setText(it.get("Nombre") as String?)
                msj4.setText(it.get("Mensaje") as String?)
            }

        val IDbtn4 = bd.collection("usuarios").document(ID.toString())
            .collection("botones").document("RaaaBnnIUawPjsiKke0k").id

        btn4.setOnClickListener{
            boton(btn4,ID,IDbtn4,msj4) }

        elm_btn4.setOnClickListener{
            elmbtn(btn4,ID,IDbtn4,msj4)
        }

    }

    private fun elmbtn (btn:Button,ID:String, IDbtn1:String, msjbtn:TextView){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar Botón")
        builder.setMessage("¿Estas segur@ de eliminar este botón?")
            with(builder){
                setPositiveButton("Borrar Botón"){
                        dialog, which ->

                    bd.collection("usuarios").document(ID)
                        .collection("botones").document(IDbtn1)
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


