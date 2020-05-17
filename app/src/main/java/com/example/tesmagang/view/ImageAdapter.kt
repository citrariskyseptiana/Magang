package com.example.tesmagang.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tesmagang.R
import com.example.tesmagang.model.Image
import kotlinx.android.synthetic.main.row_image.view.*

class ImageAdapter(private var image:List<Image>): RecyclerView.Adapter<ImageAdapter.IViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): IViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_image, parent, false)
        return IViewHolder(view)
    }

    override fun onBindViewHolder(vh: IViewHolder, position: Int) {
        //render
        vh.bind(image[position])
    }

    override fun getItemCount(): Int {
        return image.size
    }

    fun update(data:List<Image>){
        image= data
        notifyDataSetChanged()
    }

    class IViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val textViewName: TextView = view.textViewName
        private val imageView: ImageView = view.imageView
        fun bind(image:Image){
            textViewName.text = image.thumbnail
            Glide.with(imageView.context).load(image.image).into(imageView)
        }
    }
}