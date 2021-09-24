package com.example.examen

import BProfesor
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

class PProfesor : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        PBaseDeDatos.TablaProfesro = SqliteProfesor(this)
        val user = PBaseDeDatos.TablaProfesro!!.consultarProfesor()
        val listView = findViewById<ListView>(R.id.lvt_profesor)


        val adaptador = ArrayAdapter(
            this,//Contexto
            android.R.layout.simple_list_item_1, //layout (visual)
            user // arreglo
        )
        adaptador.notifyDataSetChanged()
        listView.adapter = adaptador

    }

    var posicionItemSeleccionado = 0
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pprofesor)
        PBaseDeDatos.TablaProfesro = SqliteProfesor(this)
        Log.i("bdd", PBaseDeDatos.TablaProfesro!!.databaseName.toString())

        val user = PBaseDato.arregloBProfesor
        val listView = findViewById<ListView>(R.id.lvt_profesor)


        val adaptador = ArrayAdapter(
            this,//Contexto
            android.R.layout.simple_list_item_1, //layout (visual)
            user // arreglo
        )
        adaptador.notifyDataSetChanged()
        listView.adapter = adaptador


        val botonCrear = findViewById<Button>(
            R.id.btn_crear2
        )
        botonCrear.setOnClickListener { abrirActividad( PRegistro::class.java)  }

        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflanter = menuInflater
        inflanter.inflate(R.menu.menupr, menu)

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

    fun abrirActividadConParametrosProductos(
        clase: Class<*>,
        id: Int,
        nombre: String,
        apellido: String,
        facultad: String,
        edad: String,
        estado: String,
        idcurso: String
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra(
            "profesor",
            BProfesor(id,nombre,apellido,facultad,edad,estado,idcurso)
        )
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId){
            //Editar
            R.id.mp_editar ->{
                abrirActividadConParametrosProductos(
                    PActualizar::class.java,
                    PBaseDato.arregloBProfesor.get(posicionItemSeleccionado).id,
                    PBaseDato.arregloBProfesor.get(posicionItemSeleccionado).nombre.toString(),
                    PBaseDato.arregloBProfesor.get(posicionItemSeleccionado).apellido.toString(),
                    PBaseDato.arregloBProfesor.get(posicionItemSeleccionado).facultad.toString(),
                    PBaseDato.arregloBProfesor.get(posicionItemSeleccionado).edad.toString(),
                    PBaseDato.arregloBProfesor.get(posicionItemSeleccionado).estado.toString(),
                    PBaseDato.arregloBProfesor.get(posicionItemSeleccionado).idcurso!!
                )
                onStart()

                Log.i("bdd", "Editar ${PBaseDato.arregloBProfesor[
                        posicionItemSeleccionado
                ]}")
                return true
            }

            //Eliminar
            R.id.mp_eliminar ->{
                PBaseDeDatos.TablaProfesro = SqliteProfesor(this)
                if(PBaseDeDatos.TablaProfesro != null){
                    PBaseDeDatos.TablaProfesro!!.eliminarProfesorPorId(
                        PBaseDato.arregloBProfesor.get(posicionItemSeleccionado).id!!.toInt()
                    )
                }
                onStart()
                Log.i("bdd", "Eliminar ${PBaseDato.arregloBProfesor.get(posicionItemSeleccionado).id}")
                return true
            }

            else ->  super.onContextItemSelected(item)
        }
    }
}