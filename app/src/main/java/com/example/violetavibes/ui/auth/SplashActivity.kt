package com.example.violetavibes.ui.auth
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.violetavibes.databinding.ActivitySplashBinding
import com.example.violetavibes.ui.MainActivity

class SplashActivity : AppCompatActivity() {

    // View Binding
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar view binding correctamente
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //  para navegar a MainActivity
        binding.btnEntrar.setOnClickListener {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}