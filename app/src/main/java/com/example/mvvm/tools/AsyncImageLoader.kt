package com.example.mvvm.tools

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mvvm.R
import java.lang.ref.WeakReference

object AsyncImageLoader {
    fun loadImage(imageView: ImageView?, url: String) {
        if (imageView == null || url.isEmpty()) return

        val context = WeakReference<Context>(imageView.context)

        val activity = if (context.get() is Activity) {
            context.takeIf { !(it.get() as Activity).isDestroyed } ?: return
        } else
            return

        Glide.with(activity.get() ?: return).load(url).apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.image).error(R.drawable.image_error)
            .dontAnimate()).into(imageView)
    }

    fun getBitmapImage(context: Context, url: String): Bitmap = Glide.with(context).asBitmap().load(url).submit().get()

    fun preLoadImage(context: Context, url: String) = Glide.with(context).load(url)
        .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.image).error(R.drawable.image_error)
        .dontAnimate()).preload()

    fun clear(imageView: ImageView?) {
        imageView ?: return
        Glide.with(imageView.context).clear(imageView)
    }

    fun cleanGlide(context: Context) = Glide.get(context).clearMemory()
}