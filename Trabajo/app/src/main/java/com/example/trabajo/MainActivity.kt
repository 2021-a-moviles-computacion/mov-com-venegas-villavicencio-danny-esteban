package com.example.trabajo

import BEntrenador
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val botonIrCicloVida = this.findViewById<Button>(
            R.id.btn_ciclo_vida
        )
        botonIrCicloVida.setOnClickListener { abrirActividad(ACicloVida::class.java) }

        val botonIrListView = findViewById<Button>(
            R.id.btn_ir_list_view
        )
        botonIrListView.setOnClickListener{ abrirActividad(BListView::class.java)}
        val botonIrIntent = findViewById<Button>(
            R.id.bnt_ir_intent
        )
        botonIrIntent.setOnClickListener{abrirActividadConParametros(CIntentExplicitoParametros::class.java)}
    }



    fun abrirActividad(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        // this.startActivity(intent)
        startActivity(intentExplicito)
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )

        intentExplicito.putExtra("nombre", "Adrian")
        intentExplicito.putExtra("apellido", "Eguez")
        intentExplicito.putExtra("edad", 23)
        startActivity(intentExplicito)



    }
}