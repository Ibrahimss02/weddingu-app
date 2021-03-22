package com.drunken.weddingu.models

data class CateringModel(
        val id : Int,
        val name : String,
        val alamat : String,
        val image : List<String>,
        val paketMenu : List<String>,
        val detailPaket : List<String>,
        val harga : List<Int>,
        val rating : Double,
        val kontak : String,
)
