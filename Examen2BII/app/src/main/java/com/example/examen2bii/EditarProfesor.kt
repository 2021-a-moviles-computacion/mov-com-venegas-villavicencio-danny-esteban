package com.example.examen2bii

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.examen2bii.DTO.FirestoreProfesorDto
import com.google.firebase.firestore.FirebaseFirestore

class EditarProfesor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_profesor)

        val profesoredit = intent.getParcelableExtra<FirestoreProfesorDto>("profesor")

        Log.i("list-view", "Profesor pasado ${profesoredit!!}")

        val txt_cedula_edit = findViewById<TextView>(R.id.txt_cedula_editar)
        val txt_nombre_edit = findViewById<TextView>(R.id.txt_nombre_editar)
        val txt_fecha_nac_edit = findViewById<TextView>(R.id.txt_fec_nac_editar)
        val txt_telefono_edit = findViewById<TextView>(R.id.txt_telefono_profe_editar)
        val txt_id_profesor_edit = findViewById<TextView>(R.id.txt_id_profesor_editar)
        val txt_longitud_edit = findViewById<TextView>(R.id.txt_longitud_emp)
        val txt_latitud_edit = findViewById<TextView>(R.id.txt_latitud_emp)
        val txt_id_facultad_edit = findViewById<TextView>(R.id.txt_id_facultad_prof)


        txt_id_profesor_edit.text = profesoredit!!.id.toString()
        txt_cedula_edit.text = profesoredit!!.cedula
        txt_nombre_edit.text = profesoredit!!.nombre
        txt_fecha_nac_edit.text = profesoredit!!.fechaNacimiento
        txt_telefono_edit.text = profesoredit!!.telefono.toString()
        txt_longitud_edit.text = profesoredit!!.longitud.toString()
        txt_latitud_edit.text = profesoredit!!.latitud.toString()
        txt_id_facultad_edit.text = profesoredit!!.idFacultad.toString()

        val bton_editar_profesor = findViewById<Button>(R.id.btn_edita_pro)
        bton_editar_profesor.setOnClickListener {

            val nuevoEmpresa = hashMapOf<String, Any>(
                "cedula" to txt_cedula_edit.text.toString(),
                "nombre-profesor" to txt_nombre_edit.text.toString(),
                "fecha-nacimiento" to txt_fecha_nac_edit.text.toString(),
                "telefono-profesor" to txt_telefono_edit.text.toString(),
                "latitud" to txt_latitud_edit.text.toString(),
                "longitud" to txt_longitud_edit.text.toString(),
                "id-facultad" to txt_id_facultad_edit.text.toString(),
            )

            val db = FirebaseFirestore.getInstance()
            db.collection("profesor")
                .document(profesoredit.id!!)
                .set(
                    nuevoEmpresa
                )
                .addOnSuccessListener {
                    txt_cedula_edit.text = ""
                    txt_nombre_edit.text = ""
                    txt_telefono_edit.text = ""
                    txt_fecha_nac_edit.text = ""
                }

            abrirActividadFacultad(FacultadActivity2::class.java)

        }


        val btn_canclar_editar = findViewById<Button>(R.id.btn_cancelar_edit)
        btn_canclar_editar.setOnClickListener{
            abrirActividadFacultad(FacultadActivity2::class.java)
        }


    }

    fun abrirActividadFacultad(
        clase: Class<*>,
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }
}