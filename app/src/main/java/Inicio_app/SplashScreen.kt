package Inicio_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.proyecto.ebabotndepnico.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        TiempoSplash()
        this.setTitle("EBA - Botón de Pánico");
    }

    private fun TiempoSplash(){
        object: CountDownTimer(1000, 1000){
            override fun onFinish() {
                val i = Intent(applicationContext,Historieta::class.java).apply{}
                startActivity(i)
            }

            override fun onTick(p0 : Long) {

            }

        }.start()
    }
}