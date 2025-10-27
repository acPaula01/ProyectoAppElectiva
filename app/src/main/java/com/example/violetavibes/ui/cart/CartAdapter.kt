package com.example.violetavibes.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.violetavibes.R
import com.example.violetavibes.data.CartManager
import com.example.violetavibes.model.Product

class CartAdapter(
    private var items: MutableList<Product>,
    private val onCartUpdated: () -> Unit // callback para actualizar el total
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduct: ImageView = itemView.findViewById(R.id.imgProduct)
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
        val txtQuantity: TextView = itemView.findViewById(R.id.txtQuantity)
        val btnIncrease: Button = itemView.findViewById(R.id.btnIncrease)
        val btnDecrease: Button = itemView.findViewById(R.id.btnDecrease)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = items[position]
        holder.imgProduct.setImageResource(product.imageRes)
        holder.txtName.text = product.name
        holder.txtPrice.text = "$${product.price * product.quantity}"
        holder.txtQuantity.text = product.quantity.toString()

        // Aumentar cantidad
        holder.btnIncrease.setOnClickListener {
            product.quantity++
            CartManager.updateItem(product)
            notifyItemChanged(position)
            onCartUpdated()
        }

        // Disminuir cantidad
        holder.btnDecrease.setOnClickListener {
            if (product.quantity > 1) {
                product.quantity--
                CartManager.updateItem(product)
                notifyItemChanged(position)
                onCartUpdated()
            }
        }

        // Eliminar producto
        holder.btnDelete.setOnClickListener {
            CartManager.removeItem(product)
            items = CartManager.getCartItems().toMutableList()
            notifyDataSetChanged()
            onCartUpdated()
        }
    }

    override fun getItemCount(): Int = items.size
}