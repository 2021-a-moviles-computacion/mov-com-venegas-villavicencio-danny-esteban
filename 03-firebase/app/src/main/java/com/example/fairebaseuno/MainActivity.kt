package com.example.fairebaseuno

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val CODIGO_INICIO_SESION=102
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        val botonLogin = findViewById<Button>(R.id.buttonbtn_login)
        botonLogin.setOnClickListener {
            llamarLoginUsuario()
        }
        val botonLogout = findViewById<Button>(R.id.btn_logout)
        botonLogout.setOnClickListener {
            solicitarSalirDeLaAplicacion()
        }
        val  botonProducto = findViewById<Button>(R.id.btn_producto)
        botonProducto.setOnClickListener {
            val intent = Intent(
                this, CProducto::class.java
            )
            startActivity(intent)
        }
        val  botonIrMapa = findViewById<Button>(R.id.btn_ir_mapa)
        botonIrMapa.setOnClickListener {
            val intent = Intent(
                this, FMapsActivity::class.java
            )
            startActivity(intent)
        }
        val  botonRestaurante = findViewById<Button>(R.id.btn_restaurante)
        botonRestaurante.setOnClickListener {
            val intent = Intent(
                this, DRestaurante::class.java
            )
            startActivity(intent)
        }
        val  botonOrdenes = findViewById<Button>(R.id.btn_ordenes)
        botonOrdenes.setOnClickListener {
            val intent = Intent(
                this, EOrdenes::class.java
            )
            startActivity(intent)
        }


    }
    fun  llamarLoginUsuario(){
    val providers = arrayListOf(
        //lista de proveedores
        AuthUI.IdpConfig.EmailBuilder().build()
    )
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTosAndPrivacyPolicyUrls(
                    "https://example.com/terms.html","https://example.com/privacy.html"
                ).build(), CODIGO_INICIO_SESION
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            CODIGO_INICIO_SESION->{
                if(resultCode== Activity.RESULT_OK){
                    val usuario= IdpResponse.fromResultIntent(data)
                    //if(usuario!=null){
                    if(usuario?.isNewUser==true){
                        Log.i("firebase-login","Nuevo Usuario")
                        registrarUsuarioPorPrimeraVez(usuario)
                    }else{
                        setearUsuarioFirebase()
                        Log.i("firebase-login","Usuario Antiguo")
                    }
                }else{
                    Log.i("firebase-login","El usuario Cancelo")
                }
            }
            }
        }

        fun setearUsuarioFirebase(){
            val instanciaAuth = FirebaseAuth.getInstance()
            val usuarioLocal = instanciaAuth.currentUser
            if(usuarioLocal !=null){
                if(usuarioLocal.email !=null){
                    val db= Firebase.firestore
                    val referencia = db.collection("usuario")
                        .document(usuarioLocal.email.toString())
                    referencia
                        .get().addOnSuccessListener {
                            val usuarioCargado: FirestoreUsuarioDto? = it.toObject(FirestoreUsuarioDto::class.java)
                            if(usuarioCargado!=null){
                                BAuthUsuario.usuario= BUsuarioFirebase(
                                    usuarioCargado.uid,
                                    usuarioCargado.email,
                                    usuarioCargado.roles
                                )
                                setearBienvenida()
                            }

                            Log.i("firebase-firestore", "Usuario cargado")
                        }
                        .addOnFailureListener { Log.i("firebase-firestore", "fallo cargar usuario") }
                }

            }
        }

        fun setearBienvenida(){
            val textViewBienvenida =findViewById<TextView>(R.id.tv_bienvenida)
            val botonLogin = findViewById<Button>(R.id.buttonbtn_login)
            val botonLogout = findViewById<Button>(R.id.btn_logout)
            val botonProductos = findViewById<Button>(R.id.btn_producto)
            val botonRestaurante = findViewById<Button>(R.id.btn_restaurante)
            val botonOrdenes = findViewById<Button>(R.id.btn_ordenes)
            if(BAuthUsuario.usuario != null){
                textViewBienvenida.text ="Bienvenido ${BAuthUsuario.usuario?.email}"
                botonLogin.visibility = View.INVISIBLE
                botonLogout.visibility= View.VISIBLE
                botonProductos.visibility= View.VISIBLE
                botonRestaurante.visibility= View.VISIBLE
                botonOrdenes.visibility= View.VISIBLE
            }else{
                textViewBienvenida.text= "Ingresa al aplicativo"
                botonLogin.visibility = View.VISIBLE
                botonLogout.visibility= View.INVISIBLE
                botonProductos.visibility= View.INVISIBLE
                botonRestaurante.visibility= View.INVISIBLE
                botonOrdenes.visibility= View.INVISIBLE
            }
        }
        fun registrarUsuarioPorPrimeraVez(usuario:IdpResponse){
                 val identificadorUsuario = usuario.email
                val usuaruioLogeado= FirebaseAuth.getInstance()
                    .getCurrentUser()
                if(usuario.email !=null && usuaruioLogeado != null){
                    //roles
                    val db = Firebase.firestore
                    val rolesUsuario= arrayListOf("usuario") //creamos el usuario de permisos
                    val nuevoUsuario = hashMapOf<String, Any>(
                        "roles" to rolesUsuario,
                        "uid" to usuaruioLogeado.uid,
                        "email" to identificadorUsuario.toString()
                    )

                    db.collection("usuario").document(identificadorUsuario.toString())//
                        .set(nuevoUsuario)
                        .addOnSuccessListener {
                            Log.i("firebase-firestore", "Se creo")
                            setearUsuarioFirebase()
                        }
                        .addOnFailureListener {
                            Log.i("firebase-firestore", "Fallo")
                        }


                }else {
                    Log.i("firebase-login","ERROR")
                }
            }
        fun solicitarSalirDeLaAplicacion(){
            AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener{
                    BAuthUsuario.usuario= null
                    setearBienvenida()
                }
        }

}
