package com.drunken.weddingu.models

import java.io.Serializable

data class CateringModel(
        val id: Int,
        val name: String,
        val alamat: String,
        val image: ArrayList<Int>,
        val paketMenu: Int,
        val detailPaket: List<String>,
        val harga: Int,
        val rating: Double,
        val kontak: String,
) : Serializable
