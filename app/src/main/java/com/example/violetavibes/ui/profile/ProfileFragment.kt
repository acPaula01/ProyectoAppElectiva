package com.example.violetavibes.ui.profile

import android.app.AlertDialog
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.violetavibes.R
import com.google.android.material.imageview.ShapeableImageView
import java.io.InputStream

class ProfileFragment : Fragment() {

    private lateinit var profileImage: ShapeableImageView
    private lateinit var profileName: TextView
    private lateinit var profileEmail: TextView
    private lateinit var btnEditProfile: TextView

    private var imageUri: Uri? = null

    // Launcher para abrir la galería
    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                imageUri = it
                val inputStream: InputStream? =
                    requireContext().contentResolver.openInputStream(it)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                profileImage.setImageBitmap(bitmap)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profileImage = view.findViewById(R.id.profileImage)
        profileName = view.findViewById(R.id.profileName)
        profileEmail = view.findViewById(R.id.profileEmail)
        btnEditProfile = view.findViewById(R.id.btnEditProfile)

        // Datos iniciales
        var fullName = "María López"
        var email = "maria.lopez@email.com"
        var phone = "+57 3001234567"

        btnEditProfile.setOnClickListener {
            showEditDialog(fullName, email, phone) { newName, newEmail, newPhone ->
                fullName = newName
                email = newEmail
                phone = newPhone

                profileName.text = newName
                profileEmail.text = newEmail
            }
        }

        return view
    }

    private fun showEditDialog(
        currentName: String,
        currentEmail: String,
        currentPhone: String,
        onSave: (String, String, String) -> Unit
    ) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_profile, null)
        val etName = dialogView.findViewById<EditText>(R.id.etDialogName)
        val etEmail = dialogView.findViewById<EditText>(R.id.etDialogEmail)
        val etPhone = dialogView.findViewById<EditText>(R.id.etDialogPhone)
        val btnChangePhoto = dialogView.findViewById<Button>(R.id.btnChangePhoto)

        etName.setText(currentName)
        etEmail.setText(currentEmail)
        etPhone.setText(currentPhone)

        btnChangePhoto.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Editar perfil")
            .setView(dialogView)
            .setPositiveButton("Guardar") { _, _ ->
                onSave(
                    etName.text.toString(),
                    etEmail.text.toString(),
                    etPhone.text.toString()
                )
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}