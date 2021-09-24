package com.example.ubereat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.ubereat.fragments.CuentaFragment
import com.example.ubereat.fragments.ExplorarFragment
import com.example.ubereat.fragments.InicioFragment
import com.example.ubereat.fragments.PedidosFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    //declaraando fragments
    private val cuentaFragment = CuentaFragment()
    private val explorarFragment = ExplorarFragment()
    private val inicioFragment = InicioFragment()
    private val pedidosFragment = PedidosFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(inicioFragment)
        val bt_menu = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bt_menu.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.bm_inicio-> {replaceFragment(inicioFragment)
                    Log.i("fragment","selecciono icono home")}
                R.id.bm_explorar -> replaceFragment(explorarFragment)
                R.id.bm_pedido -> replaceFragment(pedidosFragment)
                R.id.bm_cta -> replaceFragment(cuentaFragment)
            }
            true
        }

    }
    private fun replaceFragment(fragment: Fragment)
    {
        if (fragment != null)
        {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
            Log.i("fragment","ingreso a la funcion replaceFragment")
        }
    }


}