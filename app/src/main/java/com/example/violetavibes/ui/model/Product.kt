package com.example.violetavibes.model

data class Product(
    val name: String,
    val price: Int,
    val imageRes: Int,
    var quantity:Int=1
)