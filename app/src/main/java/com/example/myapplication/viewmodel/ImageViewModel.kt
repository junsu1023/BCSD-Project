package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Images
import com.example.domain.usecase.GetImageUriUseCase
import com.example.domain.usecase.GetImageUseCase
import com.example.domain.usecase.LoadImageUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImageViewModel(
    private val getImageUseCase: GetImageUseCase,
    private val getImageUriUseCase: GetImageUriUseCase,
    private val loadImageUseCase: LoadImageUseCase
): ViewModel() {
    private val _image = MutableLiveData<Images>()
    val image: LiveData<Images> get() = _image

    init {
        _image.value = getImageUseCase()
    }

    fun loadImage() = CoroutineScope(Dispatchers.IO).launch {
        loadImageUseCase()
        withContext(Dispatchers.Main) {
            _image.value = getImageUseCase()
        }
    }

    fun getImageUri(position: Int): String = getImageUriUseCase(position)
}