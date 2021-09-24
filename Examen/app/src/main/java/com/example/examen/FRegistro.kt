package com.example.examen

import SqliteFacultad
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton

class FRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fregistro)

        FBaseDeDatos.TablaFacultad = SqliteFacultad( this)
        Log.i("bdd", FBaseDeDatos.TablaFacultad!!.databaseName.toString())

        val nomfacultad = findViewById<EditText>(R.id.txt_nomfacultad)
        val materia = findViewById<EditText>(R.id.txt_materia)
        val curso = findViewById<EditText>(R.id.txt_curso)
        val numcurso = findViewById<EditText>(R.id.txt_numcurso)
        val fechaingreso = findViewById<EditText>(R.id.txt_fechaingreso)


        val botonAgregarUsuario = findViewById<Button>(
            R.id.btn_crearfacultad
        )
        botonAgregarUsuario.setOnClickListener {
            if(FBaseDeDatos.TablaFacultad != null){
                FBaseDeDatos.TablaFacultad!!.crearFacultadFormulario(
                    nomfacultad.text.toString(),
                    materia.text.toString(),
                    curso.text.toString(),
                    numcurso.text.toString(),
                    fechaingreso.text.toString(),
                    estado()
                )
                Log.i("bdd", "${nomfacultad.text}")
            }
            nomfacultad.setText("")
            materia.setText("")
            curso.setText("")
            numcurso.setText("")
            fechaingreso.setText("")
        }

    }

    fun estado(): String {
        val activo = findViewById<RadioButton>(R.id.rbd_activo)
        val pasivo = findViewById<RadioButton>(R.id.rdb_cerrado)
        var estado:String = "0"
        if(activo.isSelected() == true){
            estado = "1"
        }else if (pasivo.isSelected() == true){
            estado = "0"
        }
        return estado
    }
}