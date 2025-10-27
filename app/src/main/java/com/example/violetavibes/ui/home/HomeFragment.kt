package com.example.violetavibes.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import com.example.violetavibes.R

class HomeFragment : Fragment() {

    private lateinit var recyclerProducts: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var searchBar: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerProducts = view.findViewById(R.id.recyclerProducts)
        searchBar = view.findViewById(R.id.searchBar)

        val sampleProducts = listOf(
            Product("Vestido", "$55.000", R.drawable.imgvestido),
            Product("Blusa ", "$37.000", R.drawable.imgblusavioleta),
            Product("Falda ", "$65.000", R.drawable.imgfalda),
            Product("Corset Martina ", "$65.000", R.drawable.imgcorsetmartina),
            Product("Chaqueta Cuerina", "$60.000", R.drawable.imgchaqueta),
            Product("Chaqueta Pana ", "$77.000", R.drawable.imgchaquetapana)
        )

        productAdapter = ProductAdapter(sampleProducts) { product ->
            val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(
                productName = product.name,
                productPrice = product.price,
                productDescription = "Este es un detalle de ${product.name}",
                productImage = product.imageRes
            )
            findNavController().navigate(action)
        }

        recyclerProducts.layoutManager = LinearLayoutManager(requireContext())
        recyclerProducts.adapter = productAdapter

        // Búsqueda en tiempo real
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                productAdapter.filter(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
