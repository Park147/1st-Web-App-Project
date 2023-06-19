package com.example.teamproject.recycler

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.teamproject.databinding.ItemRecyclerviewBinding
import com.example.teamproject.model.BlankItem

class MyAlarmViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class MyAlarmAdapter(val context: Fragment, datas:MutableList<BlankItem>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable{
    var listDataFilter: MutableList<BlankItem>? = datas
    var listDataUnFilter: MutableList<BlankItem>? = datas
    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                // 필터값
                var filterString = constraint.toString()
                // listDataFilter의 값이 필터되면 listDataFiltering, 필터 안되면 listDataUnFilter
                listDataFilter = if(filterString.isEmpty()){
                    listDataUnFilter

                } else {
                    val listDataFiltering = mutableListOf<BlankItem>()
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
                listDataFilter = results?.values as MutableList<BlankItem>?
                Log.d("lmj", "최종 데이터 값 : $listDataFilter")
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyAlarmViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent,  false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyAlarmViewHolder).binding

        val waiting = listDataFilter?.get(position)
        binding.itemContent.text = waiting?.b_blank_confirm
        binding.itemtitle.text = waiting?.b_title
        binding.itemcontent.text = waiting?.b_date
        binding.itemwaiting.text = waiting?.b_time
    }
    override fun getItemCount(): Int{
        return listDataFilter?.size ?: 0
    }
}

