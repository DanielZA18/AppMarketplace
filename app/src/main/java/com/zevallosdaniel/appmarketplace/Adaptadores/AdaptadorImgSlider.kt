package com.zevallosdaniel.appmarketplace.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.zevallosdaniel.appmarketplace.Modelo.ModeloImgSlider
import com.zevallosdaniel.appmarketplace.R
import com.zevallosdaniel.appmarketplace.databinding.ItemImagenSliderBinding

class AdaptadorImgSlider: Adapter<AdaptadorImgSlider.HolderImagenSlider> {

    private lateinit var binding : ItemImagenSliderBinding
    private var context : Context
    private var imagenArrayList: ArrayList<ModeloImgSlider>

    constructor(imagenArrayList: ArrayList<ModeloImgSlider>, context: Context) {
        this.imagenArrayList = imagenArrayList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderImagenSlider {
        binding = ItemImagenSliderBinding.inflate(LayoutInflater.from(context), parent, false)
        return HolderImagenSlider(binding.root)
    }

    override fun getItemCount(): Int {
        return imagenArrayList.size
    }

    override fun onBindViewHolder(holder: HolderImagenSlider, position: Int) {
        val modeloImagenSlider = imagenArrayList[position]

        val imagenUrl = modeloImagenSlider.imagenUrl
        val imagenContador = "${position+1} / ${imagenArrayList.size}" // 1/4

        holder.imagenContadorTv.text = imagenContador
        try {
            Glide.with(context)
                .load(imagenUrl)
                .placeholder(R.drawable.ic_imagen)
                .into(holder.imagenIv)
        }catch (e: Exception){

        }

        holder.itemView.setOnClickListener {

        }
    }

    inner class HolderImagenSlider(itemView: View) : RecyclerView.ViewHolder(itemView){
        var imagenIv : ShapeableImageView = binding.ImageIv
        var imagenContadorTv : TextView = binding.imagenContadorTv
    }
}