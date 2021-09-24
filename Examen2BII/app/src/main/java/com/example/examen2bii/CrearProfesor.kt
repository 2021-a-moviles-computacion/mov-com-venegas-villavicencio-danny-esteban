package com.example.examen2bii

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.examen2bii.DTO.FirestoreFacultadDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearProfesor : AppCompatActivity() {
    var posicionItemSelecionado = 0
    //val baseDatos = BaseDatos(this)
    var idFacultad: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_profesor)

        val facultad = intent.getParcelableExtra<FirestoreFacultadDto>("facultad")

        idFacultad = facultad!!.id

        val idfacultad = findViewById<TextView>(R.id.txt_id_facultad_cre_pro)
        idfacultad.text = facultad.id.toString()


        val bt_crear_profesor = findViewById<Button>(R.id.btn_crear)

        bt_crear_profesor.setOnClickListener {
            crearProfesor()
            abrirActividad(FacultadActivity2::class.java)
        }

        val btn_cancelar = findViewById<Button>(R.id.btn_cancelar)
        btn_cancelar.setOnClickListener {
            abrirActividad(FacultadActivity2::class.java)
        }
    }


    fun crearProfesor(){
        val cedula = findViewById<TextView>(R.id.txt_cedula)
        val nombre = findViewById<TextView>(R.id.txt_nombre)
        val fechaNac = findViewById<TextView>(R.id.txt_fec_nac)
        val telefono = findViewById<TextView>(R.id.txt_telefono_pro)
        val idFacultad1 = findViewById<TextView>(R.id.txt_id_facultad_cre_pro)
        val latprofesor = findViewById<TextView>(R.id.txt_latitud)
        val longprofesor = findViewById<TextView>(R.id.txt_longitud)

        val cedulaIngreso = cedula.text.toString()
        val nombreIngreso = nombre.text.toString()
        val fechaNacIngreso = fechaNac.text.toString()
        val telefonoIngreso = telefono.text.toString()
        val latProfesorIngreso = latprofesor.text.toString()
        val longProfesorIngreso = longprofesor.text.toString()
        val idFacultadIngreso = idFacultad.toString()

        val nuevoProfesor = hashMapOf<String, Any>(
            "cedula" to cedulaIngreso,
            "nombre-profesor" to nombreIngreso,
            "fecha-nacimiento" to fechaNacIngreso,
            "telefono-profesor" to telefonoIngreso,
            "latitud" to latProfesorIngreso,
            "longitud" to longProfesorIngreso,
            "id-facultad" to idFacultadIngreso,
        )
        val db = Firebase.firestore
        val referencia = db.collection("profesor")

        referencia
            .add(nuevoProfesor)
            .addOnSuccessListener {
                cedula.text = ""
                nombre.text = ""
                fechaNac.text = ""
                telefono.text = ""
                latprofesor.text = ""
                longprofesor.text = ""
                idFacultad1.text = ""
                //abrirActividad(EmpresaActivity1::class.java)
            }
            .addOnFailureListener {
                Log.i("firestore-profesor", "no se pudo cargar los datos al firestore ")
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