package com.example.violetavibes.ui.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.violetavibes.R

class ProductDetailFragment : Fragment() {

    // Usamos SafeArgs para recibir los datos
    private val args: ProductDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productImage: ImageView = view.findViewById(R.id.productImage)
        val productName: TextView = view.findViewById(R.id.productName)
        val productDescription: TextView = view.findViewById(R.id.productDescription)
        val productPrice: TextView = view.findViewById(R.id.productPrice)
        val spinnerSize: Spinner = view.findViewById(R.id.spinnerSize)
        val btnAddToCart: Button = view.findViewById(R.id.btnAddToCart)

        // Cargar datos que vinieron del HomeFragment
        productName.text = args.productName
        productPrice.text = args.productPrice
        productDescription.text = args.productDescription
        productImage.setImageResource(args.productImage)
    }
}
