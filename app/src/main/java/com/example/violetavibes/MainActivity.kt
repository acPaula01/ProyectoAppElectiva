package com.example.violetavibes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VioletaVibesApp()
        }
    }
}

@Composable
fun VioletaVibesApp() {
    val products = listOf(
        Product("Vestido lila", 120000, "https://via.placeholder.com/150"),
        Product("Blusa blanca", 75000, "https://via.placeholder.com/150"),
        Product("Jeans mom fit", 110000, "https://via.placeholder.com/150"),
        Product("Chaqueta de jean", 180000, "https://via.placeholder.com/150")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "🛍 Violeta Vibes",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(products) { product ->
                ProductCard(product)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.imageUrl),
                contentDescription = product.name,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = product.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = "$${product.price}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}