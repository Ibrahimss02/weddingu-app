package com.drunken.weddingu.models

data class GedungModel(
    val id : Int,
    val name : String,
    val alamat : String,
    val image : ArrayList<Int>,
//    val fasilitas : String,
    val luas : Int,
    val kapasitas : Int,
    val rating : Double,
    val harga : Int,
    val kontak : String,
)


