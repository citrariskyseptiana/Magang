package com.example.tesmagang.data

import com.example.tesmagang.model.Image

interface OperationCallback<T> {
    fun onSuccess(data: List<Image>?)
    fun onError(error:String?)
}