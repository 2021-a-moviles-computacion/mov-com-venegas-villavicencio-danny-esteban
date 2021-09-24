package com.example.examen

import BFacultad
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class FActualizar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_factualizar)

        val Facultad = intent.getParcelableExtra<BFacultad>("facultad")

        val id = findViewById<TextView>(R.id.txt_id)
        val nomfacultad = findViewById<EditText>(R.id.txt_nomfacultad2)
        val materia = findViewById<EditText>(R.id.txt_materia2)
        val curso = findViewById<EditText>(R.id.txt_curso2)
        val numcurso = findViewById<EditText>(R.id.txt_numcurso2)
        val fechaingreso = findViewById<EditText>(R.id.txt_fechaingreso2)
        val activo = findViewById<RadioButton>(R.id.rbd_activo)
        val pasivo = findViewById<RadioButton>(R.id.rdb_cerrado)
        val grup = findViewById<RadioGroup>(R.id.radioGroup)

        if (Facultad != null) {
            id.setText("${Facultad.id}")
            nomfacultad.setText("${Facultad.nomfacultad}")
            materia.setText("${Facultad.materia}")
            curso.setText("${Facultad.curso}")
            numcurso.setText("${Facultad.numcurso}")
            fechaingreso.setText("${Facultad.fechaingreso}")
            if(Facultad.estado.toString() == "1"){
                grup.check(activo.id)
            }else{
                grup.check(pasivo.id)
            }
        }

        val botonActualizarUsuario = findViewById<Button>(
            R.id.btn_actualizar
        )
        botonActualizarUsuario.setOnClickListener {
            if(FBaseDeDatos.TablaFacultad != null){
                FBaseDeDatos.TablaFacultad!!.actualizarFacultadFormulario(
                    nomfacultad.text.toString(),
                    materia.text.toString(),
                    curso.text.toString(),
                    numcurso.text.toString(),
                    fechaingreso.text.toString(),
                    estado(),
                    id.text.toString().toInt()
                )
                Log.i("bdd", "${nomfacultad.text}")
            }
            id.setText("")
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