package com.example.violetavibes.data

import com.example.violetavibes.model.Product

object CartManager {
    private val cartItems = mutableListOf<Product>()

    fun addProduct(product: Product) {
        // Si ya existe, aumentar cantidad
        val existing = cartItems.find { it.name == product.name }
        if (existing != null) {
            existing.quantity += 1
        } else {
            cartItems.add(product.copy())
        }
    }

    fun removeProduct(product: Product) {
        cartItems.remove(product)
    }

    fun getCartItems(): List<Product> = cartItems

    fun clearCart() {
        cartItems.clear()
    }

    fun getTotal(): Int {
        return cartItems.sumOf { it.price * it.quantity }
    }
}