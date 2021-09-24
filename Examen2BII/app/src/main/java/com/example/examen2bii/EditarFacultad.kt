package com.example.examen2bii

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.examen2bii.DTO.FirestoreFacultadDto
import com.google.firebase.firestore.FirebaseFirestore

class EditarFacultad : AppCompatActivity() {
    var idfacultad: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_facultad)

        val facultad2 = intent.getParcelableExtra<FirestoreFacultadDto>("facultad")
        idfacultad = facultad2!!.id


        val txtIDfacultad = findViewById<TextView>(R.id.txt_id_facultad_editar)
        val txtnombre_facult = findViewById<TextView>(R.id.txt_nom_facultad_editar)
        val txtCarrera = findViewById<TextView>(R.id.txt_carrera_editar)
        val txtDireccion = findViewById<TextView>(R.id.txt_direccion_editar)
        val txtTelefono = findViewById<TextView>(R.id.txt_telefono_editar)

        txtIDfacultad.text = facultad2!!.id
        txtnombre_facult.text = facultad2!!.nomfacultad
        txtCarrera.text = facultad2!!.carrera
        txtDireccion.text = facultad2!!.direccion
        txtTelefono.text = facultad2!!.telefono

        val btn_editar_facultad = findViewById<Button>(R.id.btn_editar_facult)
        btn_editar_facultad.setOnClickListener {

            val actualizarUsuario = hashMapOf<String, Any>(
                "nombre-facultad" to txtnombre_facult.text.toString(),
                "carrera" to txtCarrera.text.toString(),
                "direccion" to txtDireccion.text.toString(),
                "telefono-ingreso" to txtTelefono.text.toString()
            )

            val db = FirebaseFirestore.getInstance()
            db.collection("facultad")
                .document(idfacultad!!)
                .set(
                    actualizarUsuario
                )
                .addOnSuccessListener {
                    txtnombre_facult.text = ""
                    txtCarrera.text = ""
                    txtTelefono.text = ""
                    txtDireccion.text = ""
                }

            abrirActividadFacultadId(FacultadActivity2::class.java)
        }


        val btn_cancelar = findViewById<Button>(R.id.bnt_cancelar_edit_facult)
            btn_cancelar.setOnClickListener {
            abrirActividadFacultadId(FacultadActivity2::class.java)
        }

    }


    fun abrirActividadFacultadId(
        clase: Class<*>,
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }


}