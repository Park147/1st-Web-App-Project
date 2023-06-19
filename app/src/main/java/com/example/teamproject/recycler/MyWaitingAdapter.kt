package com.example.teamproject.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.teamproject.databinding.ItemRecyclerviewBinding
import com.example.teamproject.model.ItemData


class MyWaitingViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class MyWaitingAdapter(val context: Fragment, datas:MutableList<ItemData>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable{
    var listDataFilter: MutableList<ItemData>? = datas
    var listDataUnFilter: MutableList<ItemData>? = datas

    // 리사이클러뷰 필터
    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                // 필터값
                var filterString = constraint.toString()
                // listDataFilter의 값이 필터되면 listDataFiltering, 필터 안되면 listDataUnFilter
                listDataFilter = if(filterString.isEmpty()){
                    listDataUnFilter
                } else {
                    val listDataFiltering = mutableListOf<ItemData>()
                    for (no in listDataUnFilter!!) {
                        if(no.toString().contains(filterString))
                            listDataFiltering.add(no)
                    }
                    listDataFiltering
                }
                // 필터결과 리턴
                val filterResults = FilterResults()
                filterResults.values = listDataFilter
                return filterResults
            }
            // 필터되면 데이터 변경
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listDataFilter = results?.values as MutableList<ItemData>?
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyWaitingViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyWaitingViewHolder).binding
        val waiting = listDataFilter?.get(position)

        binding.itemContent.text = waiting?.w_waiting_confirm
        binding.itemtitle.text = waiting?.w_title
        binding.itemcontent.text = waiting?.w_item
        binding.itemwaiting.text = waiting?.w_waiting

//        val urlImg = waiting?.w_image
//
//        Glide.with(context)
//            .asBitmap()
//            .load(urlImg)
//            .into(object : CustomTarget<Bitmap>(200, 200) {
//                override fun onResourceReady(
//                    resource: Bitmap,
//                    transition: Transition<in Bitmap>?
//                ) {
//                    binding.itemimage.setImageBitmap(resource)
//                }
//
//                override fun onLoadCleared(placeholder: Drawable?) {
//                    UCharacter.GraphemeClusterBreak.T
//                }
//            })
    }

    override fun getItemCount(): Int {
        return listDataFilter?.size ?: 0
    }

}

