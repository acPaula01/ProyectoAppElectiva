package com.example.violetavibes.ui.profile

import android.net.Uri
import androidx.lifecycle.*
import com.example.violetavibes.data.AppDao
import com.example.violetavibes.data.UserEntity
import kotlinx.coroutines.launch

class ProfileViewModel(private val dao: AppDao) : ViewModel() {

    private val currentUserId = 1
    val user: LiveData<UserEntity?> = dao.getUserById(currentUserId).asLiveData()

    private val _newImageUri = MutableLiveData<Uri?>()

    fun saveUserProfile(name: String, email: String, phone: String) {
        viewModelScope.launch {
            val currentUser = user.value ?: UserEntity(id = currentUserId, name = "", email = "", phone = "")

            val imageUriString = _newImageUri.value?.toString() ?: currentUser.imageUri

            val updatedUser = currentUser.copy(
                name = name,
                email = email,
                phone = phone,
                imageUri = imageUriString
            )

            dao.insertOrUpdateUser(updatedUser)
            _newImageUri.value = null
        }
    }

    fun setNewImageUri(uri: Uri?) {
        _newImageUri.value = uri
    }
}
class ProfileViewModelFactory(private val dao: AppDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


