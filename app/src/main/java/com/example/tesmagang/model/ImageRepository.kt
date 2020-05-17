package com.example.tesmagang.model

import android.media.Image
import android.util.Log

import com.example.tesmagang.data.ApiClient
import com.example.tesmagang.data.ImageResponse
import com.example.tesmagang.data.OperationCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG="CONSOLE"

class ImageRepository:ImageDataSource {

    private var call: Call<ImageResponse>?=null

    override fun retrieveImage(callback: OperationCallback<Image>) {
        call=ApiClient.build()?.images()
        call?.enqueue(object : Callback<ImageResponse> {
            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
                response.body()?.let {
                    if(response.isSuccessful){
                        Log.v(TAG, "data ${it.data}")
                        callback.onSuccess(it.data)
                    }else{
                        callback.onError(it.msg)
                    }
                }
            }
        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}