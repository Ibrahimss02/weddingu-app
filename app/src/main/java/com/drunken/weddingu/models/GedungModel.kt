package com.drunken.weddingu.models

data class GedungModel(
    val id : Int,
    val name : String,
    val alamat : String,
    val image : List<String>,
    val fasilitas : String,
    val luas : Double,
    val kapasitas : Int,
    val rating : Double,
    val harga : Int,
    val kontak : String,
)


