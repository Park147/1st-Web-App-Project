package com.example.a1st_web_app_project

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a1st_web_app_project.databinding.ItemMainBinding


class MyViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val context: Context, val datas: List<ItemModel2>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding


        //도보 여행
        val user = datas?.get(position)
        binding.firstNameView.text = user?.RSTR_NM
        binding.contactView.text = user?.RSTR_TELNO
        binding.contactView.text = user?.RSTR_RDNMADR
        binding.contactView.text = user?.BSNS_STATM_BZCND_NM
        binding.contactView.text = user?.RSTR_INTRCN_CONT
//        val urlImg = user?.MAIN_IMG_NORMAL
//
//        Glide.with(context)
//            .asBitmap()
//            .load(urlImg)
//            .into(object : CustomTarget<Bitmap>(200, 200) {
//                override fun onResourceReady(
//                    resource: Bitmap,
//                    transition: Transition<in Bitmap>?
//                ) {
//                    binding.avatarView.setImageBitmap(resource)
////                    Log.d("hjm", "width : ${resource.width}, height: ${resource.height}")
//                }
//
//                override fun onLoadCleared(placeholder: Drawable?) {
//                    TODO("Not yet implemented")
//                }
//            })

        //add......................................
      /*  val user = datas?.get(position)
        binding.firstNameView.text = user?.RSTR_NM
        val urlImg = user?.FOOD_IMG_URL

        Glide.with(context)
            .asBitmap()
            .load(urlImg)
            .into(object : CustomTarget<Bitmap>(200, 200) {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    binding.avatarView.setImageBitmap(resource)
                    Log.d("lsy", "width : ${resource.width}, height: ${resource.height}")
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })*/

    }
}