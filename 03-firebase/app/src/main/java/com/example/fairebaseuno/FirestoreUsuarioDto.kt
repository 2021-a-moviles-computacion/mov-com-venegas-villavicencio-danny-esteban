package com.example.fairebaseuno

data class FirestoreUsuarioDto (
        val uid: String ="",
        val email:String ="",
        var roles:ArrayList<String> = arrayListOf()
        )