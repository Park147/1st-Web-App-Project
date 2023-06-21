package com.example.teamproject.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teamproject.MyApplication
import com.example.teamproject.R
import com.example.teamproject.databinding.ItemRecyclerviewBinding
import com.example.teamproject.model.BlankItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyAlarmViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root) {
    val button: Button = itemView.findViewById(R.id.item_button)
}

class MyAlarmAdapter(val context: Fragment, datas:MutableList<BlankItem>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable{
    private var listData: MutableList<BlankItem>? = datas

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                // 필터값
                var filterString = constraint.toString()
                // listDataFilter의 값이 필터되면 listDataFiltering, 필터 안되면 listDataUnFilter

                val filteredList = mutableListOf<BlankItem>()

                if (filterString.isEmpty()) {
                    filteredList.addAll(listData!!)
                } else {
                    for (BlankItem in listData!!) {
                        if (BlankItem.toString().contains(filterString)) {
                            filteredList.add(BlankItem)
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
                listData = results?.values as MutableList<BlankItem>?
                notifyDataSetChanged()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyAlarmViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent,  false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyAlarmViewHolder).binding

        val blank = listData?.get(position)
        binding.itemContent.text = blank?.b_blank_confirm
        binding.itemtitle.text = blank?.b_title
        binding.itemcontent.text = blank?.b_date
        binding.itemwaiting.text = blank?.b_time

        var img = blank?.b_image

        if (context != null && img != null) {
            Glide.with(context)
                .load(img)
                .into(binding.itemimage)
        }

        holder.button.setOnClickListener {
            val blank = listData?.get(position)

            val networkService = MyApplication.getInstance().userService
            val reserveDeleteCall = networkService.deleteBlankList(blank?.b_title)

            reserveDeleteCall.enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Log.d("lmj", "성공")
                    listData?.remove(blank)
                    notifyDataSetChanged()
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("lmj", "실패 : ${t.message}")
                }
            })

        }

    }
    override fun getItemCount(): Int{
        return listData?.size ?: 0
    }
}
