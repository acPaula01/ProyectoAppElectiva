// Ruta del archivo: app/src/main/java/com/example/violetavibes/data/ProductAdapter.kt

package com.example.violetavibes.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.violetavibes.R

// ✅ Usa ProductEntity, que es la clase correcta de tu base de datos
class ProductAdapter(
    private var productList: List<ProductEntity>,
    private val onItemClicked: (ProductEntity) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // Clase interna que representa cada "tarjeta" de producto en la lista
    // CORRECTO
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.itemProductImage)
        val productName: TextView = itemView.findViewById(R.id.itemProductName)
        val productPrice: TextView = itemView.findViewById(R.id.itemProductPrice)
    }


    // Crea nuevas vistas (invocado por el RecyclerView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        // Asegúrate de tener un layout llamado 'item_product.xml' en 'res/layout'
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

        // Configura el click para ir al detalle del producto
        holder.itemView.setOnClickListener {
            onItemClicked(product)
        }
    }

    // Devuelve el número total de items
    override fun getItemCount() = productList.size

    // ✅ Define la función 'filterList' que faltaba
    // Esta función es llamada por la barra de búsqueda para actualizar la lista
    fun filterList(filteredList: List<ProductEntity>) {
        productList = filteredList
        notifyDataSetChanged() // Notifica al RecyclerView que los datos cambiaron
    }
}


