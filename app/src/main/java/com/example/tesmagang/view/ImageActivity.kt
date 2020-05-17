package com.example.tesmagang.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tesmagang.R
import com.example.tesmagang.di.Injection
import com.example.tesmagang.model.Image
import com.example.tesmagang.viewmodel.ImageViewModel
import kotlinx.android.synthetic.main.activity_image.*
import kotlinx.android.synthetic.main.layout_error.*

class ImageActivity : AppCompatActivity() {
    private lateinit var viewModel: ImageViewModel
    private lateinit var adapter: ImageAdapter

    companion object {
        const val TAG= "CONSOLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        setupViewModel()
        setupUI()
    }

    //ui
    private fun setupUI(){
        adapter= ImageAdapter((viewModel.images.value?: emptyList()) as List<Image>)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter= adapter
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory()).get(ImageViewModel::class.java)
        viewModel.images.observe(this,renderImages)

        viewModel.isViewLoading.observe(this,isViewLoadingObserver)
        viewModel.onMessageError.observe(this,onMessageErrorObserver)
        viewModel.isEmptyList.observe(this,emptyListObserver)
    }

    //observers
    private val renderImages= Observer<List<Image>> {
        Log.v(TAG, "data updated $it")
        layoutError.visibility=View.GONE
        layoutEmpty.visibility=View.GONE
        adapter.update(it)
    }

    private val isViewLoadingObserver= Observer<Boolean> {
        Log.v(TAG, "isViewLoading $it")
        val visibility=if(it)View.VISIBLE else View.GONE
        progressBar.visibility= visibility
    }

    private val onMessageErrorObserver= Observer<Any> {
        Log.v(TAG, "onMessageError $it")
        layoutError.visibility=View.VISIBLE
        layoutEmpty.visibility=View.GONE
        textViewError.text= "Error $it"
    }

    private val emptyListObserver= Observer<Boolean> {
        Log.v(TAG, "emptyListObserver $it")
        layoutEmpty.visibility=View.VISIBLE
        layoutError.visibility=View.GONE
    }

    //If you require updated data, you can call the method "loadMuseum" here
    override fun onResume() {
        super.onResume()
        viewModel.loadImage()
    }

}
