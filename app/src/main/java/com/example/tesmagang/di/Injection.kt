package com.example.tesmagang.di

import androidx.lifecycle.ViewModelProvider
import com.example.tesmagang.model.ImageDataSource
import com.example.tesmagang.model.ImageRepository
import com.example.tesmagang.viewmodel.ImageViewModel
import com.example.tesmagang.viewmodel.ViewModelFactory

object Injection {

    private val imageDataSource:ImageDataSource = ImageRepository()
    private val imageViewModelFactory = ViewModelFactory(imageDataSource)

    fun providerRepository():ImageDataSource{
        return imageDataSource
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory{
        return imageViewModelFactory
    }
}