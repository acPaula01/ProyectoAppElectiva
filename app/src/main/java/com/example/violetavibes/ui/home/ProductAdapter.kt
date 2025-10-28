package com.example.violetavibes.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.violetavibes.R


class ProductAdapter(
    private var productList: List<ProductEntity>,
    private val onItemClicked: (ProductEntity) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // Clase interna que representa cada "tarjeta" de producto en la lista
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.itemProductImage)
        val productName: TextView = itemView.findViewById(R.id.itemProductName)
        val productPrice: TextView = itemView.findViewById(R.id.itemProductPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    // Vincula los datos de un producto a una vista
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productImage.setImageResource(product.imageRes)
        holder.productName.text = product.name
        holder.productPrice.text = product.price
        holder.itemView.setOnClickListener {
            onItemClicked(product)
        }
    }

    // Devuelve el número total de items
    override fun getItemCount() = productList.size

    // Esta función es llamada por la barra de búsqueda para actualizar la lista
    fun filterList(filteredList: List<ProductEntity>) {
        productList = filteredList
        notifyDataSetChanged() // Notifica al RecyclerView que los datos cambiaron
    }
}


