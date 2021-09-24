package com.example.fairebaseuno

class Producto(val nombre: String? = null,val precio: Double?=null) {

    override fun toString(): String {
        return nombre!!
    }
}