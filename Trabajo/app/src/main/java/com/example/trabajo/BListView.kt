package com.example.trabajo
import BEntrenador
import android.app.AlertDialog
import android.content.DialogInterface
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
import com.example.trabajo.R

class BListView : AppCompatActivity() {
    var posicionItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        val arregloNumeros = arrayListOf<BEntrenador>(
            BEntrenador("Adrian", "a@a.com"),
            BEntrenador("Juan", "j@j.com"),
            BEntrenador("Manuel", "m@m.com"),

            )
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloNumeros
        )
        val listViewEjemplo: ListView = findViewById<ListView>(R.id.ltv_ejemplo)
        listViewEjemplo.adapter = adaptador

        val botonAnadirNumero = findViewById<Button>(R.id.btn_anadir_numero)
        botonAnadirNumero.setOnClickListener {
            añadirItemsAlListView(
                BEntrenador("Prueba","d@d.com"),
                arregloNumeros,
                adaptador
            )

        }
        /*  listViewEjemplo
              .setOnItemLongClickListener { adapterView, view, posicion, id ->
                  Log.i("List-view", "Dio clic ${posicion}" )
                  return@setOnItemLongClickListener true*/
        registerForContextMenu(listViewEjemplo)
    }




    fun añadirItemsAlListView(
        valor: BEntrenador,
        arreglo: ArrayList<BEntrenador>,
        adaptador: ArrayAdapter<BEntrenador>
    ){
        arreglo.add(valor)
        adaptador.notifyDataSetChanged()//actualiza la interfaz
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
        Log.i("List-view", "List View ${posicionItemSeleccionado}")
        Log.i("List-view", "Entrenador ${BBaseDatosMemoria.arregloBEntrenador[id]}")


    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId){
            //Editar
            R.id.m_editar ->{
                Log.i("List-view", "Editar ${BBaseDatosMemoria.arregloBEntrenador[
                        posicionItemSeleccionado
                ]}")
                return true
            }

            //Eliminar
            R.id.m_eliminar ->{
                Log.i("List-view", "Eliminar ${BBaseDatosMemoria.arregloBEntrenador[
                        posicionItemSeleccionado
                ]}")
                return true
            }
            else ->  super.onContextItemSelected(item)
        }
    }
}