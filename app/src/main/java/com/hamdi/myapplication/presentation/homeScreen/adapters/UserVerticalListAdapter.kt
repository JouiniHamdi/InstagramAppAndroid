package com.hamdi.myapplication.presentation.homeScreen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hamdi.myapplication.R
import com.hamdi.myapplication.domain.model.Photo

class UserVerticalListAdapter(private var photoList: List<Photo>) :
    RecyclerView.Adapter<UserVerticalListAdapter.UserViewHolder>() {


    var itemClickListener: ((position: Int, photo: Photo) -> Unit)? = null
    var emptyVerticalListListener: ((isEmpty: Boolean) -> Unit)? = null


    fun setPhotosVerticalList(newPhotoList: List<Photo>) {
        photoList = newPhotoList
        notifyDataSetChanged()
        if (photoList.isNullOrEmpty()){
            emptyVerticalListListener?.invoke(true)
        }else{
            emptyVerticalListListener?.invoke(false)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.photos_vertical_list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val photo = photoList[position]

        showPhoto(photo, holder)

        holder.tvUserName.text = photo.user?.name ?: ""
        holder.tvCreationDate.text = photo.createdAt

        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(position, photo)
        }

    }


    override fun getItemCount(): Int {
        return photoList.size
    }

    private fun showPhoto(photo: Photo, holder: UserViewHolder) {
        Glide.with(holder.itemView.context)
            .load(photo.urls?.small)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.ivVerticalList)
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUserName = itemView.findViewById(R.id.tv_user_name) as TextView
        val tvCreationDate = itemView.findViewById(R.id.tv_creation_date) as TextView
        val ivVerticalList = itemView.findViewById(R.id.iv_vertical_list) as ImageView

    }

}