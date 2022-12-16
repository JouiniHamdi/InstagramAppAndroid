package com.hamdi.myapplication.presentation.photo_full_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hamdi.myapplication.R

class FullScreenPhotoFragment : Fragment() {
    var photoUrl: String = ""
    lateinit var ivFullScreen: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_full_screen_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        getAllArguments()
        showPhoto(photoUrl)

    }

    private fun getAllArguments(){
        photoUrl = arguments.let { it?.getSerializable("photoUrl") as String }
    }

    private fun initViews(view: View){
        ivFullScreen = view.findViewById<ImageView>(R.id.iv_full_screen)
     }


private fun showPhoto(photoUrl: String ) {
    Glide.with(this)
        .load(photoUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(ivFullScreen)
}


}