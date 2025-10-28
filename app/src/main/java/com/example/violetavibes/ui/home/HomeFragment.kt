package com.example.violetavibes.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.violetavibes.R
import com.example.violetavibes.data.AppDatabase
import com.example.violetavibes.data.ProductEntity
import com.example.violetavibes.data.ProductAdapter
import kotlinx.coroutines.launch
class HomeFragment : Fragment() {

    // Declaraciones de las vistas y el adaptador
    private lateinit var recyclerProducts: RecyclerView
    private lateinit var searchBar: EditText
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout para este fragmento
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicialización de vistas
        recyclerProducts = view.findViewById(R.id.recyclerProducts)
        searchBar = view.findViewById(R.id.searchBar)

        val db = AppDatabase.getDatabase(requireContext())
        val dao = db.appDao()

        // Configuración inicial de RecyclerView
        recyclerProducts.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {

            if (dao.getAllProducts().isEmpty()) {
                val sampleProducts = listOf(
                    ProductEntity(name = "Vestido", price = "$55.000", imageRes = R.drawable.imgvestido),
                    ProductEntity(name = "Blusa", price = "$37.000", imageRes = R.drawable.imgblusavioleta),
                    ProductEntity(name = "Falda", price = "$65.000", imageRes = R.drawable.imgfalda),
                    ProductEntity(name = "Corset Martina", price = "$65.000", imageRes = R.drawable.imgcorsetmartina),
                    ProductEntity(name = "Chaqueta Cuerina", price = "$60.000", imageRes = R.drawable.imgchaqueta),
                    ProductEntity(name = "Chaqueta Pana", price = "$77.000", imageRes = R.drawable.imgchaquetapana)
                )
                sampleProducts.forEach { dao.insertProduct(it) }
            }

            // Obtener productos desde la base de datos
            val productsFromDb = dao.getAllProducts()


            productAdapter = ProductAdapter(productsFromDb) { product ->
                val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(
                    productName = product.name,
                    productPrice = product.price,
                    productDescription = "Este es un detalle de ${product.name}",
                    productImage = product.imageRes
                )
                findNavController().navigate(action)
            }

            recyclerProducts.adapter = productAdapter
        }

        // Búsqueda en tiempo real
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lifecycleScope.launch {
                    val filtered = if (s.isNullOrEmpty()) {
                        dao.getAllProducts()
                    } else {
                        dao.searchProducts("%${s}%")
                    }// Pasa la lista 'filtered' directamente
                    productAdapter.filterList(filtered)

                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
