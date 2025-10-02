package com.example.violetavibes.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.violetavibes.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Buscar el botón en el layout
        val btnGoToDetail: Button = view.findViewById(R.id.btnGoToDetail)

        // Acción al hacer clic → navegar al fragmento de detalle
        btnGoToDetail.setOnClickListener {
            findNavController().navigate(R.id.productDetailFragment)
        }
    }
}