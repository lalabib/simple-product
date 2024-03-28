package com.lalabib.latihan.simpleproduct.utils

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
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

    fun ImageView.disableView() {
        isEnabled = false
        alpha = 0.5f
        setColorFilter(ContextCompat.getColor(context, R.color.grey))
        isClickable = false
    }

    fun ImageView.enableView() {
        isEnabled = true
        alpha = 1f
        setColorFilter(ContextCompat.getColor(context, R.color.blue_500))
        isClickable = true
    }

    val Context.createDataStore: DataStore<Preferences> by preferencesDataStore("simpleProduct")
}