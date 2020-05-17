package com.example.tesmagang.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tesmagang.model.ImageDataSource

class ViewModelFactory(private val repository:ImageDataSource):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImageViewModel(repository) as T
    }
}