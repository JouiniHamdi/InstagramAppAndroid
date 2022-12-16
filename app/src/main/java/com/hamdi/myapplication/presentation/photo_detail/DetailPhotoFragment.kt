package com.hamdi.myapplication.presentation.photo_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hamdi.myapplication.R


class DetailPhotoFragment : Fragment() {

    var photoUrl: String = ""
    var userName: String = ""
    var userProfilePicture: String = ""
    var createdAt: String = ""
    var updatedAt: String = ""

    lateinit var ivFullScreen: ImageView
    lateinit var ivUserPicture: ImageView
    lateinit var tvUserName: TextView
    lateinit var tvCreatedAt: TextView
    lateinit var tvUpdatedAt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAllArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_detail_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)

        showPhoto(photoUrl,ivFullScreen)
        showPhoto(userProfilePicture,ivUserPicture)

        tvUserName.text = userName
        tvCreatedAt.text = createdAt
        tvUpdatedAt.text = updatedAt


    }

    private fun getAllArguments(){
        photoUrl = arguments.let { it?.getSerializable("photoUrl") as String }
        userName = arguments.let { it?.getSerializable("userName") as String }
        createdAt = arguments.let { it?.getSerializable("createdAt") as String }
        updatedAt = arguments.let { it?.getSerializable("updatedAt") as String }
        userProfilePicture = arguments.let { it?.getSerializable("userProfilePicture") as String }
    }

    private fun initViews(view: View){
        ivFullScreen = view.findViewById(R.id.iv_full_screen)
        tvUserName = view.findViewById(R.id.tv_user_name)
        ivUserPicture = view.findViewById(R.id.iv_user_picture)
        tvCreatedAt = view.findViewById(R.id.tv_created_at)
        tvUpdatedAt = view.findViewById(R.id.tv_updated_at)
     }


    private fun showPhoto(photoUrl: String , imageView: ImageView) {
        Glide.with(this)
            .load(photoUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }


}