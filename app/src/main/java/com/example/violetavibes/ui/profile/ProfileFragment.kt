package com.example.violetavibes.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.violetavibes.R
import com.example.violetavibes.data.AppDatabase
import com.google.android.material.imageview.ShapeableImageView

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var toggleEditButton: TextView
    private lateinit var profileImageView: ShapeableImageView
    private lateinit var profileNameTextView: TextView
    private lateinit var profileEmailTextView: TextView

    private var isEditMode = false

    private val selectImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            // Persistir el permiso para acceder a la URI
            requireContext().contentResolver.takePersistableUriPermission(
                it,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            profileImageView.setImageURI(it)
            viewModel.setNewImageUri(it) // Pasa la nueva URI al ViewModel
            Toast.makeText(requireContext(), "Imagen seleccionada. Pulsa 'Guardar'", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Infla el layout del fragmento.
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicialización del ViewModel ---
        val dao = AppDatabase.getDatabase(requireContext()).appDao()
        val factory = ProfileViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)

        // Inicialización de Vistas ---
        nameEditText = view.findViewById(R.id.etFullName)
        emailEditText = view.findViewById(R.id.etEmail)
        phoneEditText = view.findViewById(R.id.etPhone)
        saveButton = view.findViewById(R.id.btnSaveProfile)
        toggleEditButton = view.findViewById(R.id.btnToggleEdit)
        profileImageView = view.findViewById(R.id.ivProfileImage)
        // Inicialización de nuevas vistas
        profileNameTextView = view.findViewById(R.id.tvProfileName)
        profileEmailTextView = view.findViewById(R.id.tvProfileEmail)
        // Inicialización de nuevas vistas

        //  Observación de Datos
        viewModel.user.observe(viewLifecycleOwner) { userEntity ->
            if (userEntity != null) {
                // Actualiza los campos de texto del formulario
                if (nameEditText.text.toString() != userEntity.name) nameEditText.setText(userEntity.name)
                if (emailEditText.text.toString() != userEntity.email) emailEditText.setText(userEntity.email)
                if (phoneEditText.text.toString() != userEntity.phone) phoneEditText.setText(userEntity.phone)

                //  Actualización de los textos debajo de la foto
                profileNameTextView.text = userEntity.name.ifEmpty { "Nombre de Usuario" }
                profileEmailTextView.text = userEntity.email.ifEmpty { "usuario@email.com" }
                // Actualización de los textos debajo de la foto

                // Carga la imagen desde la URI guardada en la base de datos
                if (!userEntity.imageUri.isNullOrEmpty()) {
                    try {
                        val imageUri = Uri.parse(userEntity.imageUri)
                        profileImageView.setImageURI(imageUri)
                    } catch (e: Exception) {
                        profileImageView.setImageResource(R.drawable.ic_profile)
                    }
                } else {
                    profileImageView.setImageResource(R.drawable.ic_profile)
                }
            }
        }

        // Configuración de Eventos
        toggleEditButton.setOnClickListener {
            isEditMode = !isEditMode
            updateUiForEditMode()
        }

        saveButton.setOnClickListener {
            viewModel.saveUserProfile(
                nameEditText.text.toString(),
                emailEditText.text.toString(),
                phoneEditText.text.toString()
            )
            isEditMode = false
            updateUiForEditMode()
            Toast.makeText(requireContext(), "Perfil guardado correctamente", Toast.LENGTH_SHORT).show()
        }

        profileImageView.setOnClickListener {
            if (isEditMode) {
                selectImageLauncher.launch("image/*")
            } else {
                Toast.makeText(requireContext(), "Pulsa 'Editar' para cambiar la foto", Toast.LENGTH_SHORT).show()
            }
        }
        //  Estado Inicial de la UI ---
        updateUiForEditMode()
    }
    private fun updateUiForEditMode() {
        if (isEditMode) {
            toggleEditButton.text = "Cancelar"
            saveButton.isVisible = true
            nameEditText.isEnabled = true
            emailEditText.isEnabled = true
            phoneEditText.isEnabled = true
        } else {
            toggleEditButton.text = "Editar"
            saveButton.isVisible = false
            nameEditText.isEnabled = false
            emailEditText.isEnabled = false
            phoneEditText.isEnabled = false
        }
    }
}








