package com.example.fairebaseuno

class Restaurante (val nombre: String? = null) {

    override fun toString(): String {
        return nombre!!
    }
}