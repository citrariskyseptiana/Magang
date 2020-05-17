package com.example.tesmagang.model

import android.media.Image
import com.example.tesmagang.data.OperationCallback

interface ImageDataSource {

    fun retrieveImage(callback: OperationCallback<Image>)
    fun cancel()
}