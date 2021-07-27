package com.example.examen

import BProfesor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class PActualizar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pactualizar)

        val profesor = intent.getParcelableExtra<BProfesor>("profesor")

        val id = findViewById<TextView>(R.id.txe_id)
        val nombre = findViewById<EditText>(R.id.txe_nombre2 )
        val apellido = findViewById<EditText>(R.id.txe_apellido2)
        val facultad = findViewById<EditText>(R.id.txe_facultad2)
        val edad= findViewById<EditText>(R.id.txe_edad2)
        val estado = findViewById<EditText>(R.id.txe_estado2)
        var idcurso = findViewById<EditText>(R.id.txe_idcurso2)

        if (profesor != null) {
            id.setText("${profesor.id}")
            nombre.setText("${profesor.nombre}")
            apellido.setText("${profesor.apellido}")
            facultad.setText("${profesor.facultad}")
            edad.setText("${profesor.edad}")
            estado.setText("${profesor.estado}")
            idcurso.setText("${profesor.idcurso}")
        }

        val botonActualizarProfesor = findViewById<Button>(
            R.id.btn_actualizarprofesor
        )
        botonActualizarProfesor.setOnClickListener {

            if(PBaseDeDatos.TablaProfesro != null){
                PBaseDeDatos.TablaProfesro!!.actualizarProfesorFormulario(
                    nombre.text.toString(),
                    apellido.text.toString(),
                    facultad.text.toString(),
                    edad.text.toString(),
                    estado.text.toString(),
                    idcurso.text.toString(),
                    id.text.toString().toInt()
                )
                Log.i("bdd", "${nombre.text} -- ${apellido.text}")
            }
            id.setText("")
            nombre.setText("")
            apellido.setText("")
            facultad.setText("")
            edad.setText("")
            estado.setText("")
            idcurso.setText("")
        }

    }
}