package com.example.violetavibes.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete


@Dao
interface AppDao {

    // --- PRODUCTOS ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<ProductEntity>

    @Query("SELECT * FROM products WHERE name LIKE :query")
    suspend fun searchProducts(query: String): List<ProductEntity>

    // --- CARRITO ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(item: CartEntity)

    @Delete
    suspend fun deleteCartItem(item: CartEntity)

    @Query("SELECT * FROM cart")
    suspend fun getCartItems(): List<CartEntity>

    @Query("DELETE FROM cart")
    suspend fun clearCart()
}