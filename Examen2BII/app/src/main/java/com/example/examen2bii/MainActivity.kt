package com.example.examen2bii

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ir_aplicacion = findViewById<Button>(R.id.btn_iniciar_sistema)

        ir_aplicacion.setOnClickListener {
            irAṕlicacion()
        }
    }

    fun irAṕlicacion() {
        val intentExplicito = Intent(
            this,
            FacultadActivity2::class.java
        )
        startActivity(intentExplicito)

    }

}