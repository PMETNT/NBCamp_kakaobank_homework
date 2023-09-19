package com.example.nbcamp_kakaobank_homework

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nbcamp_kakaobank_homework.databinding.ItemRecyclerBinding
import com.example.nbcamp_kakaobank_homework.image_data.Document
import com.example.nbcamp_kakaobank_homework.image_data.ImageSearch

class SearchAdapter : RecyclerView.Adapter<Holder>() {

    var homework_imageList: ImageSearch? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val image = homework_imageList?.documents?.get(position)
        holder.setImage(image)

    }

    override fun getItemCount(): Int {
        return homework_imageList?.documents?.size?: 0
    }
}

class Holder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {

    fun setImage(image: Document?) {
        image?.let {
            binding.textTitle.text = image.display_sitename
            binding.textDate.text = image.datetime
            Glide.with(binding.imageItem).load(it.thumbnail_url).into(binding.imageItem)

        }
    }



}
