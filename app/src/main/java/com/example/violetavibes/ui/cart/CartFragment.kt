package com.example.violetavibes.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.violetavibes.R
import com.example.violetavibes.data.CartManager

class CartFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvTotal: TextView
    private lateinit var btnCheckout: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        recyclerView = view.findViewById(R.id.recyclerCart)
        tvTotal = view.findViewById(R.id.tvTotal)
        btnCheckout = view.findViewById(R.id.btnCheckout)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = CartAdapter(CartManager.getCartItems())

        // Mostrar el total
        updateTotal()

        // Acción del botón de pago
        btnCheckout.setOnClickListener {
            // Lógica para navegar a una pantalla de pago o mostrar un mensaje
            CartManager.clearCart()
            recyclerView.adapter?.notifyDataSetChanged()
            updateTotal()
        }

        return view
    }

    private fun updateTotal() {
        tvTotal.text = "Total: $${CartManager.getTotal()}"
    }
}