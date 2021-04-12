package com.proyecto.ebabotndepnico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


enum class ProviderType {
    BASIC
}

class Mostrar_Datos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar__datos)
    }
}