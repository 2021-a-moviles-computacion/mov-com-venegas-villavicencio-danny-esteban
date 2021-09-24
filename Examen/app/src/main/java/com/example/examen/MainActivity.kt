package com.example.examen

import BFacultad
import SqliteFacultad
import SqliteProfesor
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
       FBaseDeDatos.TablaFacultad = SqliteFacultad(this)
        val proveedores =FBaseDeDatos.TablaFacultad!!.consultarListaFacultad()
        val listView = findViewById<ListView>(R.id.ltv_facultad)

        val adaptador = ArrayAdapter(
            this,//Contexto
            android.R.layout.simple_list_item_1, //layout (visual)
            proveedores// arreglo
        )
        adaptador.notifyDataSetChanged()
        listView.adapter = adaptador
    }

    var posicionItemSeleccionado = 0
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FBaseDeDatos.TablaFacultad = SqliteFacultad(this)
        Log.i("bdd", FBaseDeDatos.TablaFacultad!!.databaseName.toString())

        val facultad = FBaseDatos.arregloBFacultad
        val listView = findViewById<ListView>(R.id.ltv_facultad)

        val adaptador = ArrayAdapter(
            this,//Contexto
            android.R.layout.simple_list_item_1, //layout (visual)
            facultad// arreglo
        )
        adaptador.notifyDataSetChanged()
        listView.adapter = adaptador

        val botonCrear = findViewById<Button>(
            R.id.btn_crear
        )
        botonCrear.setOnClickListener { abrirActividad(FRegistro::class.java) }

        registerForContextMenu(listView)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflanter = menuInflater
        inflanter.inflate(R.menu.menu, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItemSeleccionado = id
    }

    fun abrirActividad(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }

    fun abrirActividadProfesor(
        clase: Class<*>,
        numcurso: Int,
        nomfacultad: String
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("numcurso",numcurso)
        intentExplicito.putExtra("nomfacultad",nomfacultad)
        startActivity(intentExplicito)
    }

    fun abrirActividadConParametros(
        clase: Class<*>,
        id: Int,
        nomfacultad: String,
        materia: String,
        curso: String,
        numcurso: String,
        fechaingreso: String,
        estado: String
    ){
        val intentExplicito = Intent(
            this,
            clase
        )

        intentExplicito.putExtra(
            "facultad",
            BFacultad(id,nomfacultad,materia,curso,numcurso,fechaingreso,estado)
        )
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId){
            //Editar
            R.id.mi_editar ->{
                abrirActividadConParametros(
                    FActualizar::class.java,
                    FBaseDatos.arregloBFacultad.get(posicionItemSeleccionado).id,
                    FBaseDatos.arregloBFacultad.get(posicionItemSeleccionado).nomfacultad.toString(),
                    FBaseDatos.arregloBFacultad.get(posicionItemSeleccionado).materia.toString(),
                    FBaseDatos.arregloBFacultad.get(posicionItemSeleccionado).curso.toString(),
                    FBaseDatos.arregloBFacultad.get(posicionItemSeleccionado).numcurso.toString(),
                    FBaseDatos.arregloBFacultad.get(posicionItemSeleccionado).fechaingreso.toString(),
                    FBaseDatos.arregloBFacultad.get(posicionItemSeleccionado).estado!!
                )

                onStart()

                Log.i("bdd", "Editar ${FBaseDatos.arregloBFacultad[
                        posicionItemSeleccionado
                ]}")
                return true
            }

            //Eliminar
            R.id.mi_eliminar ->{

                PBaseDeDatos.TablaProfesro = SqliteProfesor(this)
                if(PBaseDeDatos.TablaProfesro != null){
                    PBaseDeDatos.TablaProfesro!!.eliminarProfesorPorId(
                        PBaseDato.arregloBProfesor.get(posicionItemSeleccionado).idcurso!!.toInt()
                    )
                }

                FBaseDeDatos.TablaFacultad = SqliteFacultad(this)
                if(FBaseDeDatos.TablaFacultad != null){
                    FBaseDeDatos.TablaFacultad!!.eliminarFacultadPorId(
                        FBaseDatos.arregloBFacultad.get(posicionItemSeleccionado).numcurso!!.toInt()
                    )
                }
                onStart()

                Log.i("bdd", "Eliminar ${FBaseDatos.arregloBFacultad.get(posicionItemSeleccionado).numcurso}")
                return true
            }

            R.id.mi_ver_profesores->{
                abrirActividadProfesor(
                    PProfesor::class.java,
                    FBaseDatos.arregloBFacultad.get(posicionItemSeleccionado).id,
                    FBaseDatos.arregloBFacultad.get(posicionItemSeleccionado).nomfacultad.toString()
                )
                Log.i("bdd", "Ver ${FBaseDatos.arregloBFacultad[
                        posicionItemSeleccionado
                ]} y id ${FBaseDatos.arregloBFacultad.get(posicionItemSeleccionado).numcurso} ")
                return true
            }
            else ->  super.onContextItemSelected(item)
        }
    }
}