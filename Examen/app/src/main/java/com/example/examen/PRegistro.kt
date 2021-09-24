package com.example.examen

import PBaseDeDatos
import SqliteProfesor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class PRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregistro)


        PBaseDeDatos.TablaProfesro = SqliteProfesor(this)
        Log.i("bdd", PBaseDeDatos.TablaProfesro!!.databaseName.toString())

        val nombre = findViewById<EditText>(R.id.txe_nombre)
        val apellido = findViewById<EditText>(R.id.txe_apellido)
        val facultad = findViewById<EditText>(R.id.txe_facultad)
        val edad = findViewById<EditText>(R.id.txe_edad)
        val estado = findViewById<EditText>(R.id.txe_estado)
        val idcurso = findViewById<EditText>(R.id.txe_curso)

        val botonAgregarProducto = findViewById<Button>(
            R.id.btn_regsitroprofesor
        )
        botonAgregarProducto.setOnClickListener {
            if(PBaseDeDatos.TablaProfesro != null){
                PBaseDeDatos.TablaProfesro!!.crearProfesor(
                    nombre.text.toString(),
                    apellido.text.toString(),
                    facultad.text.toString(),
                    edad.text.toString(),
                    estado.text.toString(),
                    idcurso.text.toString()
                )
                Log.i("bdd", "${nombre.text} ${apellido.text}")
            }
            nombre.setText("")
            apellido.setText("")
            facultad.setText("")
            edad.setText("")
            estado.setText("")
            idcurso.setText("")
        }

    }
}