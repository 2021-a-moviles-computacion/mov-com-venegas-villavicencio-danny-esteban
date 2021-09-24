package com.example.examen2bii

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.examen2bii.DTO.FirestoreProfesorDto
import com.example.examen2bii.DTO.FirestoreFacultadDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfesorActivity : AppCompatActivity() {
    var posiconElementoSeleccionado = 0
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 400
    var idfacultad2: String? = ""
    var profesorSeleccioanda: FirestoreProfesorDto? = null
    var adpatador: ArrayAdapter<FirestoreProfesorDto>? = null
    var arregloProfesores = arrayListOf<FirestoreProfesorDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profesor)

        //CARGAR INFORMACION DE EMPRESA DEL EMPLEADO
        val facultad3 = intent.getParcelableExtra<FirestoreFacultadDto>("facultad")
        idfacultad2 = facultad3!!.id
        Log.i("firestore-profesor","{$idfacultad2}")
        val idFAcultad4 = findViewById<TextView>(R.id.txt_nombrefacultad_profesor)
        idFAcultad4.text = facultad3.nomfacultad


        cargarProfesor(idfacultad2!!)
        adpatador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloProfesores

        )

        val listViewProfesor = findViewById<ListView>(R.id.list_view_profesores)
        listViewProfesor.adapter = adpatador
        listViewProfesor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long)
            {
                profesorSeleccioanda = arregloProfesores[position]
            }

            override fun onNothingSelected(
                p0: AdapterView<*>?)
            {
                Log.i("firestore-profesor", "No ha seleccionado ningun item")
            }
        }


        registerForContextMenu(listViewProfesor)

        val btNuevoProfesor = findViewById<Button>(R.id.btn_nuevo_profesor4)
        btNuevoProfesor.setOnClickListener {
            abrirActiviad(CrearProfesor::class.java, facultad3!!)
        }

    }

    @SuppressLint("LongLogTag")
    fun cargarProfesor(idProfesor1: String){
        val db = Firebase.firestore
        val referencia = db.collection("profesor")

        referencia
            .whereEqualTo("id-facultad", idProfesor1)
            .get()
            .addOnSuccessListener {
                for (document in it){
                    // Log.i("EMPRESA-SELECCIONADA-CARGA ", "${idEmpresa}")
                    var empleado = document.toObject(FirestoreProfesorDto::class.java)
                    empleado!!.id = document.id
                    empleado!!.cedula = document.get("cedula").toString()
                    empleado!!.fechaNacimiento = document.get("fecha-nacimiento").toString()
                    empleado!!.nombre = document.get("nombre-profesor").toString()
                    empleado!!.telefono = document.get("telefono-profesor").toString()
                    empleado!!.latitud = document.get("latitud").toString()
                    empleado!!.longitud = document.get("longitud").toString()
                    empleado!!.idFacultad = document.get("id-facultad").toString()

                    arregloProfesores.add(empleado)
                    adpatador?.notifyDataSetChanged()

                }
            }
            .addOnFailureListener {
                Log.i("NO INGRESO AL ADD ON SUCCES FILE LISTENER  ", "${idProfesor1}")
            }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menuprofesor,menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position

        posiconElementoSeleccionado = id

        Log.i("list-view","onCreate ${id}")

    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        var profesorSel = arregloProfesores[posiconElementoSeleccionado]
        return when(item?.itemId){
            // Editar
            R.id.men_editar_profe -> {
                Log.i("list-view", "Editar ${profesorSel} ")
                abrirActiviadProfesor(EditarProfesor::class.java, profesorSel)
                return true
            }
            //Eliinar
            R.id.men_eliminar_profe-> {
                Log.i("list-view", "Eliminar ${profesorSel} ")
                val db = FirebaseFirestore.getInstance()
                db.collection("profesor")
                    .document(profesorSel.id!!)
                    .delete()
                    .addOnSuccessListener {
                        adpatador?.remove(adpatador!!.getItem(posiconElementoSeleccionado))
                        adpatador?.notifyDataSetChanged()
                    }

                return true
            }
            R.id.men_ver_mapa -> {
                abrirActiviadProfesor(Mapas::class.java, profesorSel)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActiviadProfesor(
        clase: Class<*>,
        profesor: FirestoreProfesorDto
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("profesor", profesor)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    fun abrirActiviad(
        clase: Class<*>,
        facultad: FirestoreFacultadDto
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("facultad", facultad)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }


}