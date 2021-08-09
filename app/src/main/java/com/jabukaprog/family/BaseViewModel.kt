package com.jabukaprog.family

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jabukaprog.family.data.databases.PhotoModelEntity
import com.jabukaprog.family.repository.DatabaseRepository
import com.jabukaprog.family.repository.LocalDataRepository
import com.jabukaprog.family.repository.PhotosPagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor
    (
    @ApplicationContext context: Context,
    private val photosPagingRepository: PhotosPagingRepository,
    private val localDataRepository: LocalDataRepository,
    private val databaseRepository: DatabaseRepository,
) : ViewModel() {

    private fun getPhotos() = photosPagingRepository.getPhotosOne()

    init {
        loadSharedPref()
        getPhotos()
        loadFromDb()
    }

    var loadDb by mutableStateOf<List<PhotoModelEntity>>(mutableListOf())

    fun insertPhotoModelDB(photoModelDB: PhotoModelEntity) {
        databaseRepository.insertPhotoModelDB(photoModelDB = photoModelDB)
    }

    fun loadFromDb() {
        viewModelScope.launch {
            loadDb = databaseRepository.getPhotos()
        }
    }

    fun saveSharedPref(uri: String) {
        val stringUriSet = setOf(uri)
        viewModelScope.launch {
            localDataRepository.setData(stringUriSet)
        }
        loadSharedPref()
    }

    var loadStringUri = mutableSetOf("")

    fun loadSharedPref() {
        viewModelScope.launch {
            if (localDataRepository.getStringsOrNull() == null) {
                return@launch
            } else {
                loadStringUri = localDataRepository.getStringsOrNull()!!
            }
        }
    }

}