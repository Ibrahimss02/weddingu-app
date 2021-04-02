package com.drunken.weddingu.models

import java.io.Serializable

data class GedungModel(
    val id : Int = 0,
    val name : String = "",
    val alamat : String = "",
    val image : ArrayList<Int> = ArrayList(),
//    val fasilitas : String,
    val luas : Int = 0,
    val kapasitas : Int = 0,
    val rating : Double = 0.0,
    val harga : Int = 0,
    val kontak : String = "",
) : Serializable


