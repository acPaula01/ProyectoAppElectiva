package com.example.violetavibes.data

import com.example.violetavibes.model.Product

object CartManager {
    private val cartItems = mutableListOf<Product>()

    // Agregar producto al carrito
    fun addProduct(product: Product) {
        val existingProduct = cartItems.find { it.name == product.name && it.price == product.price }
        if (existingProduct != null) {
            existingProduct.quantity += product.quantity
        } else {
            cartItems.add(product)
        }
    }

    // Actualizar (reemplazar) un producto en el carrito
    fun updateItem(product: Product) {
        val index = cartItems.indexOfFirst { it.name == product.name && it.price == product.price }
        if (index != -1) {
            cartItems[index] = product
        }
    }

    // Eliminar un producto (sin usar removeIf)
    fun removeItem(product: Product) {
        val iterator = cartItems.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item.name == product.name && item.price == product.price) {
                iterator.remove()
                break
            }
        }
    }

    // Obtener todos los productos
    fun getCartItems(): List<Product> = cartItems

    // Calcular total (entero, ya que price es Int)
    fun getTotal(): Int {
        return cartItems.sumOf { it.price * it.quantity }
    }

    // Vaciar carrito
    fun clearCart() {
        cartItems.clear()
    }
}