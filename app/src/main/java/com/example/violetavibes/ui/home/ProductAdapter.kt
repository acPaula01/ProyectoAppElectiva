package com.example.violetavibes.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.violetavibes.R

// Modelo de datos
data class Product(val name: String, val price: String, val imageRes: Int)

class ProductAdapter(
    private val products: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // Lista interna filtrada
    private var filteredProducts: MutableList<Product> = products.toMutableList()

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = view.findViewById(R.id.itemProductImage)
        val productName: TextView = view.findViewById(R.id.itemProductName)
        val productPrice: TextView = view.findViewById(R.id.itemProductPrice)

        init {
            view.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val product = filteredProducts[position]
                    onItemClick(product)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = filteredProducts[position]
        holder.productName.text = product.name
        holder.productPrice.text = product.price
        holder.productImage.setImageResource(product.imageRes)
    }

    override fun getItemCount() = filteredProducts.size

    // Filtrar productos por nombre
    fun filter(query: String) {
        filteredProducts = if (query.isEmpty()) {
            products.toMutableList()
        } else {
            products.filter { it.name.contains(query, ignoreCase = true) }.toMutableList()
        }
        notifyDataSetChanged()
    }
}

