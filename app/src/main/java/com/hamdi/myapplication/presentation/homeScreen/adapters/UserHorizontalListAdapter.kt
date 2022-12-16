package com.hamdi.myapplication.presentation.homeScreen.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hamdi.myapplication.R
import com.hamdi.myapplication.domain.model.Photo
import de.hdodenhof.circleimageview.CircleImageView

class UserHorizontalListAdapter (private var photoList: List<Photo>) : RecyclerView.Adapter<UserHorizontalListAdapter.UserViewHolder>(){

    var itemClickListener: ((position: Int, photoUrl: String, photoId : String) -> Unit)? = null
    var emptyListListener: ((isEmpty: Boolean) -> Unit)? = null

 fun setPhotosHorizontalList(newPhotoList : List<Photo>){
     photoList = newPhotoList
     notifyDataSetChanged()
     if (photoList.isNullOrEmpty()){
         emptyListListener?.invoke(true)
     }else{
         emptyListListener?.invoke(false)
     }
 }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photos_horizontal_list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
           val photo = photoList[position]

         if (photo.isAlreadySeen){
             holder.ivHorizontalList.borderColor =  Color.parseColor("#FFACACAC")
         }

        holder.tvUserName.text = photo.user?.name ?: ""
        holder.ivHorizontalList

        showPhoto(photo,holder)


        holder.itemView.setOnClickListener {
             itemClickListener?.invoke(position, photo.urls?.full ?: "" , photo.id?: "")
        }


    }


    
    private fun showPhoto(photo : Photo, holder: UserViewHolder){
        Glide.with(holder.itemView.context)
            .load(photo.urls?.small)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.ivHorizontalList)
    }

    override fun getItemCount(): Int {
      return  photoList.size
     }



    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvUserName = itemView.findViewById(R.id.tv_user_name) as TextView
        val ivHorizontalList = itemView.findViewById(R.id.iv_horizontal_list) as CircleImageView
    }


}