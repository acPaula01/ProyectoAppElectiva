package com.example.violetavibes.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import com.example.violetavibes.R

class HomeFragment : Fragment() {

    private lateinit var recyclerProducts: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referencia al RecyclerView
        recyclerProducts = view.findViewById(R.id.recyclerProducts)

        // Lista de prueba
        val sampleProducts = listOf(
            Product("Vestido", "$55.000", R.drawable.ic_launcher_background),
            Product("Blusa Casual", "$37.000", R.drawable.ic_launcher_background),
            Product("Falda Elegante", "$45.000", R.drawable.ic_launcher_background)
        )


        productAdapter = ProductAdapter(sampleProducts) { product ->

            findNavController().navigate(R.id.productDetailFragment)
        }

        recyclerProducts.layoutManager = LinearLayoutManager(requireContext())
        recyclerProducts.adapter = productAdapter
    }
}
