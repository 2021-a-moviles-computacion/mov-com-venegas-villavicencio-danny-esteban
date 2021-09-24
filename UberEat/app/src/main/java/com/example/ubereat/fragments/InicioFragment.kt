package com.example.ubereat.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ubereat.Dato
import com.example.ubereat.Pedido
import com.example.ubereat.R
import com.example.ubereat.adapters.InicioAdapter


class InicioFragment : Fragment() {

    private lateinit var inicioAdapter: InicioAdapter //es no null,pero se inicializará más luego
    private lateinit var listaPedido: ArrayList<Pedido>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_inicio, container, false)
    }

    private fun addDataSet()
    {
        listaPedido = Dato.listaDatos
        inicioAdapter.submitList(listaPedido)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        initRecyclerView()
        addDataSet()

    }

    private fun initRecyclerView()
    {
        val rv_inicio= requireView().findViewById<RecyclerView>(R.id.rv_inicio)

        rv_inicio.apply {
            rv_inicio.layoutManager = LinearLayoutManager(activity)
            inicioAdapter = InicioAdapter()
            rv_inicio.adapter = inicioAdapter

        }



    }


}