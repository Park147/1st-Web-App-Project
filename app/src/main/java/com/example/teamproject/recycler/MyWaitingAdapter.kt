package com.example.teamproject.recycler

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.teamproject.MyApplication
import com.example.teamproject.MyDining
import com.example.teamproject.OnItemClickListener
import com.example.teamproject.R
import com.example.teamproject.databinding.ItemRecyclerviewBinding
import com.example.teamproject.fragment.OneFragment
import com.example.teamproject.model.ItemData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyWaitingViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root) {
    val button: Button = itemView.findViewById(R.id.item_button)
}

class MyWaitingAdapter(val context: Fragment, datas:MutableList<ItemData>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable{
    private var listData: MutableList<ItemData>? = datas
    private lateinit var listener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    // 리사이클러뷰 필터
    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                // 필터값
                val filterString = constraint.toString()
                // 필터 결과 저장할 변수
                val filteredList = mutableListOf<ItemData>()

                if (filterString.isEmpty()) {
                    filteredList.addAll(listData!!)
                } else {
                    for (itemData in listData!!) {
                        if (itemData.toString().contains(filterString)) {
                            filteredList.add(itemData)
                        }
                    }
                }

                // 필터결과 리턴
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            // 필터되면 데이터 변경
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listData = results?.values as MutableList<ItemData>?
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyWaitingViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyWaitingViewHolder).binding
        val waiting = listData?.get(position)

        binding.itemContent.text = waiting?.w_waiting_confirm
        binding.itemtitle.text = waiting?.w_title
        binding.itemcontent.text = waiting?.w_item
        binding.itemwaiting.text = waiting?.w_waiting

        binding.root.setOnClickListener {
            listener.onItemClick("${waiting?.w_title}")
        }

        holder.button.setOnClickListener {
            waiting?.w_waiting_confirm ="방문취소"
            val waiting = listData?.get(position)

            val networkService = MyApplication.getInstance().networkService
            val reserveDeleteCall = networkService.update(waiting)

            reserveDeleteCall.enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Log.d("lmj", "성공")
                    listData?.remove(waiting)
                    notifyDataSetChanged()
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("lmj", "실패 : ${t.message}")
                }
            })

        }

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
        return listData?.size ?: 0
    }

}

