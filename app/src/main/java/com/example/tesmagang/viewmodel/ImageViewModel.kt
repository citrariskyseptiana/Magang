package com.example.tesmagang.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tesmagang.data.OperationCallback
import com.example.tesmagang.model.ImageDataSource

class ImageViewModel(private val repository: ImageDataSource): ViewModel() {

    private val _images = MutableLiveData<List<com.example.tesmagang.model.Image>>().apply { value = emptyList() }
    val images: LiveData<List<com.example.tesmagang.model.Image>> = _images

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError

    private val _isEmptyList=MutableLiveData<Boolean>()
    val isEmptyList:LiveData<Boolean> = _isEmptyList

    fun loadImage(){
        _isViewLoading.postValue(true)
        repository.retrieveImage(object:OperationCallback<com.example.tesmagang.model.Image>{
            override fun onError(error: String?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue( error)
            }

            override fun onSuccess(data: List<com.example.tesmagang.model.Image>?) {
                _isViewLoading.postValue(false)

                if(data!=null){
                    if(data.isEmpty()){
                        _isEmptyList.postValue(true)
                    }else _images.value= data
                }
            }
        })
    }

}