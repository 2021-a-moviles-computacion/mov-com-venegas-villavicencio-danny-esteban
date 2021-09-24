package com.example.examen2bii

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.examen2bii.DTO.FirestoreFacultadDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FacultadActivity2 : AppCompatActivity() {
    var facultadSeleccioanda: FirestoreFacultadDto? = null
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 400
    var adapter: ArrayAdapter<FirestoreFacultadDto>? = null
    var arregloFacultad = arrayListOf<FirestoreFacultadDto>()
    var  posiconElementoSeleccionado: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facultad2)

        cargarFacultad()
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloFacultad
        )

        val listViewFacuktad = findViewById<ListView>(R.id.list_view_facultada)
        listViewFacuktad.adapter = adapter
        listViewFacuktad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long)
            {
                facultadSeleccioanda = arregloFacultad[position]
            }

            override fun onNothingSelected(
                p0: AdapterView<*>?)
            {
                Log.i("firestore-facultad", "No ha seleccionado ningun item")
            }
        }

        val btnCrearFacultad = findViewById<Button>(R.id.btn_crear_facuñltad_inicio)
        btnCrearFacultad.setOnClickListener {
            abrirActiviad(CrearFacultad::class.java)
        }

        registerForContextMenu(listViewFacuktad)
    }

    fun cargarFacultad(){
        val db = Firebase.firestore
        val referencia = db.collection("facultad")

        referencia
            .get()
            .addOnSuccessListener {
                for (document in it){
                    var empresa = document.toObject(FirestoreFacultadDto::class.java)
                    empresa!!.id = document.id
                    empresa!!.carrera = document.get("carrera").toString()
                    empresa!!.nomfacultad = document.get("nombre-facultad").toString()
                    empresa!!.direccion = document.get("direccion").toString()
                    empresa!!.telefono = document.get("telefono-ingreso").toString()

                    arregloFacultad.add(empresa)
                    adapter?.notifyDataSetChanged()
                }
            }
            .addOnFailureListener {
                Log.i("firebase-firestore", "no se cargaron los datos al listView")
            }
    }

    fun abrirActiviad(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menuinicial,menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position

        posiconElementoSeleccionado = id

        // Log.i("list-view","onCreate ${id}")
        // Log.i("list-view","Usuario ${baseDatos.consultarEmpresas()[id].id}")

    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        var idElemento = arregloFacultad[posiconElementoSeleccionado]
        return when(item?.itemId){
            // Editar
            R.id.men_editar_facul -> {
                abrirActividadporId(EditarFacultad::class.java, idElemento)
                return true
            }

            //Eliinar
            R.id.men_eliminar_facul -> {
                Log.i("list-view", "Eliminar ${idElemento.id}")
                val db = FirebaseFirestore.getInstance()
                db.collection("facultad")
                    .document(idElemento.id!!)
                    .delete()
                    .addOnSuccessListener {
                        abrirActiviad(FacultadActivity2::class.java)
                    }
                return true
            }



            R.id.men_ver_profesures_facu -> {
                abrirActividadporId(ProfesorActivity::class.java, idElemento)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }



    fun abrirActividadporId(
        clase: Class<*>,
        facultad: FirestoreFacultadDto
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("facultad", facultad)
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

}