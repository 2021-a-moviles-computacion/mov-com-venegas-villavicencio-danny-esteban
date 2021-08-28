package com.example.ubereat.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ubereat.Pedido
import com.example.ubereat.R

class InicioAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Pedido> = ArrayList()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder)
        {
            is InicioViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return InicioViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_inicio_recycler,
                parent,
                false
            )/*, mListener*/
        )
    }

    class InicioViewHolder constructor(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        val imagen = view.findViewById<ImageView>(R.id.img_receta)
        val nombreLocal = view.findViewById<TextView>(R.id.et_nombre_local)
        val precio = view.findViewById<TextView>(R.id.et_precio)
        val tiempo = view.findViewById<TextView>(R.id.et_tiempo)
        val puntuacion = view.findViewById<TextView>(R.id.et_puntuacion)


        fun bind(pedido: Pedido) {
            imagen.setImageResource(pedido.imagenPedido)
            nombreLocal.setText(pedido.nombreLocal)
            precio.setText(pedido.costoEnvio.toString())
            tiempo.setText(pedido.tiempo)
            puntuacion.setText(pedido.puntuacion.toString())
        }


    }



}