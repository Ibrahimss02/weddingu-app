package com.drunken.weddingu.models

data class StudioFotoModel(
        val id : Int,
        val name : String,
        val alamat : String,
        val imageHasil : String,
        val paket : List<String>,
        val harga : List<Int>,
        val rating : Double,
        val kontak : String,
)
