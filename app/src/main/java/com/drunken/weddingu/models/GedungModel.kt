package com.drunken.weddingu.models

import android.media.Image

data class GedungModel(
    val id : Int,
    val name : String,
    val alamat : String,
    val image : String,
    val luas : Double,
    val kapasitas : Int,
    val rating : Double,
    val tarif : Double,
    val kontak : Long
)

data class FotoGedung(
        val name: String,
        val image : String
)


