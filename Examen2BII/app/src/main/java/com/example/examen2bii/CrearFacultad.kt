package com.example.examen2bii

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearFacultad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_facultad)

        val btn_guardar = findViewById<Button>(R.id.btn_crear_facultad)

        btn_guardar.setOnClickListener{
            crearFacultad()
        }

        val btn_cancelar = findViewById<Button>(R.id.btn_cancelar_facultad)
        btn_cancelar.setOnClickListener {
            abrirActividad(FacultadActivity2::class.java)
        }


    }


    fun crearFacultad(){


        val nombrefacul = findViewById<TextView>(R.id.txt_nombre_facultad)
        val carreara1 = findViewById<TextView>(R.id.txt_carrera)
        val direccion = findViewById<TextView>(R.id.txt_direccion)
        val telefono = findViewById<TextView>(R.id.txt_telefono_facultad)

        val nomFaculIngreso = nombrefacul.text.toString()
        val carreraIngreso = carreara1.text.toString()
        val direccionIngreso = direccion.text.toString()
        val telefonoIngreso = telefono.text.toString()

        val nuevaFacultad = hashMapOf<String, Any>(
            "nombre-facultad" to nomFaculIngreso,
            "carrera" to carreraIngreso,
            "direccion" to direccionIngreso,
            "telefono-ingreso" to telefonoIngreso
        )
        val db = Firebase.firestore
        val referencia = db.collection("facultad")

        referencia
            .add(nuevaFacultad)
            .addOnSuccessListener {
                nombrefacul.text = ""
                carreara1.text = ""
                direccion.text = ""
                telefono.text = ""

                abrirActividad(FacultadActivity2::class.java)

            }
            .addOnFailureListener {
                Log.i("firestore-facultad", "no se pudo cargar los datos al firestore ")
            }


    }

    fun abrirActividad(
        clase: Class<*>,
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }

}