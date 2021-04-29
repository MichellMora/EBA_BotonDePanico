package Inicio_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.proyecto.ebabotndepnico.R
import com.proyecto.ebabotndepnico.Tratamiento_Datos

class Historieta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historieta)

        TiempoHisto()
    }

    private fun TiempoHisto(){
        object: CountDownTimer(15000, 1000){
            override fun onFinish() {
                val i = Intent(applicationContext,Tratamiento_Datos::class.java).apply{}
                startActivity(i)
            }

            override fun onTick(p0 : Long) {

            }

        }.start()
    }
}