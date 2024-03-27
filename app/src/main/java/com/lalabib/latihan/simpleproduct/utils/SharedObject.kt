package com.lalabib.latihan.simpleproduct.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lalabib.latihan.simpleproduct.R

object SharedObject {

    fun loadImage(imageView: ImageView, avatar: String?) {
        Glide.with(imageView.context)
            .load(avatar)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_broken_img)
            )
            .into(imageView)
    }
}