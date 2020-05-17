package com.example.tesmagang.data

import com.example.tesmagang.model.Image

data class ImageResponse(val text:String?,val msg:String?,val data:List<Image>?){
    fun isSuccess():String?= (text)
}