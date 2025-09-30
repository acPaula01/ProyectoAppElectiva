package com.example.violetavibes.ui.auth
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
// Importación de la clase View Binding generada automáticamente
import com.example.violetavibes.databinding.ActivitySplashBinding
import com.example.violetavibes.ui.MainActivity

class SplashActivity : AppCompatActivity() {

    // 1. Declaración de la variable View Binding
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Inicialización de View Binding (usa el layout activity_splash.xml)
        binding = ActivitySplashBinding.inflate(layoutInflater)

        // 3. Establecer la vista raíz
        setContentView(binding.root)

        // 4. Asignación del Listener al botón 'btn_entrar' para la navegación
        binding.btnEntrar.setOnClickListener {
            // Crear Intent para ir a la MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Finalizar la actividad de Splash para que no se pueda volver
            finish()
        }
    }
}