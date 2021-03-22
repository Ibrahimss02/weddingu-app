package com.drunken.weddingu.models

data class EventOrganizerModel(
        val id : Int,
        val name : String,
        val alamat : String,
        val image : List<String>,
        val paketAdat : List<String>,
        val detailPaket : List<String>,
        val harga : List<Int>,
        val rating : Double,
        val kontak : String,
)
