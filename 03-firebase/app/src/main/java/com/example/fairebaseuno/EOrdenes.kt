package com.example.fairebaseuno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
class EOrdenes : AppCompatActivity() {

    private lateinit var spinerProducto: Spinner
    private lateinit var spinerDRestaurante : Spinner
    var Itemseleccionado = ""
    var PrecioProducto = 0.0
    var totalAPagar = 0.0

    val itemProductoSeleccionado = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val adaptadorListView: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemProductoSeleccionado)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eordenes)
        spinerDRestaurante = findViewById(R.id.sp_restaurantes)
        spinerProducto = findViewById(R.id.sp_producto)
        CargarEspinerProductos()
        CargarEspinerRestaurante()

        val lvProducto = findViewById<ListView>(R.id.lv_lista_productos)

        lvProducto.adapter = adaptadorListView
        val botonAdd = findViewById<Button>(R.id.btn_anadir_lista_producto)
        botonAdd.setOnClickListener {

            anadirOrden()
        }
    }
    fun CargarEspinerProductos(){

        val listproducto: MutableList<Producto> = obtenerProductoRestaurante("producto") as MutableList<Producto>
        val adaptadorProducto: ArrayAdapter<Producto> = ArrayAdapter<Producto>(this, android.R.layout.simple_spinner_item, listproducto)
        val listViewProducto = findViewById<ListView>(R.id.lv_lista_productos)
        Adaptar(adaptadorProducto, spinerProducto, "producto")
        val itemProductosSeleccionados = arrayListOf<String>()
        val adaptadorListView: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemProductosSeleccionados)
        listViewProducto.adapter = adaptadorListView
            }

    fun CargarEspinerRestaurante(){

        var restaurante :(MutableList<DocumentSnapshot>)
        var arregloDeRestaurante= ArrayList<String>()
        val db = Firebase.firestore
        val referencia = db.collection("restaurante").get()
            .addOnSuccessListener{
                restaurante = it.documents
                restaurante.forEach{iteracion ->
                    arregloDeRestaurante.add(iteracion.get("nombre").toString())

                }
                val lista =arregloDeRestaurante.toList()
                val adaptadorRestaurante = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, lista)
                spinerDRestaurante.adapter = adaptadorRestaurante


            }
    }
    private fun obtenerProductoRestaurante(coleccion: String): MutableList<*> {
        val ListaProducto = mutableListOf<Producto>()
        val listaRestaurante = mutableListOf<Restaurante>()
        val db = Firebase.firestore
        val referencia = db.collection(coleccion)
        return when (coleccion) {
            "producto" -> {
                referencia.get().addOnSuccessListener { documents ->
                    for (document in documents) {
                        val producto = document.toObject(Producto::class.java)
                        ListaProducto.add(producto)
                    }
                }
                ListaProducto.add(Producto("", 0.00))
                ListaProducto

            }
            else -> {
                referencia.get().addOnSuccessListener { documents ->
                    for (document in documents) {
                        val restaurante = document.toObject(Restaurante::class.java)
                        listaRestaurante.add(restaurante)
                    }
                }
                listaRestaurante.add(Restaurante(""))
                listaRestaurante
            }
        }
    }

    fun anadirOrden() {

        val sumaTotal = findViewById<TextView>(R.id.tv_total)
        val cantidad = findViewById<EditText>(R.id.et_cantidad_producto)

        val cantidadProducto = cantidad.text.toString()
        if (cantidadProducto != "" && cantidadProducto.toInt() > 0) {
            val CantidadProducto = cantidadProducto.toInt()
            val total = PrecioProducto * CantidadProducto
            val subtotal:Double = String.format("%.2f", total).toDouble()
            val adaptadorListView: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemProductoSeleccionado)
            val mostrarlistview =
                "\t$Itemseleccionado\t\t\tCantidad:$CantidadProducto\t\t\tPrecio Unitario:$PrecioProducto\t\t\tSubtotal: $subtotal  "
            totalAPagar += subtotal
            val numbeTotlar2digits:Double = String.format("%.2f", totalAPagar).toDouble()
            sumaTotal.text = numbeTotlar2digits.toString()
            itemProductoSeleccionado.add(mostrarlistview)
            adaptadorListView.notifyDataSetChanged()
            cantidad.text.clear()
        }
    }
    private fun Adaptar(adaptador: ArrayAdapter<*>, spinner: Spinner, nombreClase: String) {
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adaptador
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                if (nombreClase == "producto") {
                    val itemProductoSel: Producto = parent?.getItemAtPosition(position) as Producto
                    Itemseleccionado = itemProductoSel.nombre!!
                    PrecioProducto = itemProductoSel.precio!!
                } else {
                    val itemRestauranteSel: Restaurante = parent?.getItemAtPosition(position) as Restaurante
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.i("firebase-firestore", "Item no seleccionado")
            }
        }
    }
}