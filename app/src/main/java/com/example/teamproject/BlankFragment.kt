package com.example.teamproject

import com.example.teamproject.recycler.MyAdapter


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamproject.databinding.FragmentBlankBinding
import com.example.teamproject.model.PageListModel
import com.example.teamproject.model.PageListModel2
import retrofit2.Callback
import retrofit2.Response

class BlankFragment : Fragment() {

    private lateinit var binding: FragmentBlankBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val serviceKey = "DLqEcgtBqy4lMOUw1BMi8NL1H5XiTbNKGuQtPM3epTpiPJZa6jcwQo1DDRmsGstF"

        val networkService = (requireActivity().applicationContext as MyApplication).networkService
        val rsListCall = networkService.getList(serviceKey)
        val rsImgListCall = networkService.getImgList(serviceKey)
        Log.d("lsy", "url:" + rsListCall.request().url().toString())
        Log.d("lsy", "url:" + rsImgListCall.request().url().toString())

        rsListCall.enqueue(object : Callback<PageListModel> {
            override fun onResponse(
                call: retrofit2.Call<PageListModel>,
                response: Response<PageListModel>
            ) {
                val rsList = response.body()

                Log.d("lsy", "rsList data 값 : ${rsList?.body}")
                Log.d("lsy", "rsList data 갯수 : ${rsList?.body?.size}")

                rsImgListCall.enqueue(object : Callback<PageListModel2> {
                    override fun onResponse(
                        call: retrofit2.Call<PageListModel2>,
                        response: Response<PageListModel2>
                    ) {
                        val rsImgList = response.body()

                        Log.d("lsy", "rsImgList data 값 : ${rsImgList?.body}")
                        Log.d("lsy", "rsImgList data 갯수 : ${rsImgList?.body?.size}")
                        Log.d("lsy", "rsList 이름 : ${rsList?.body?.get(0)?.RSTR_NM}")
                        Log.d("lsy", "rsImgList 이름 : ${rsImgList?.body?.get(0)?.RSTR_NM}")
                        for (i in 0 until (rsList?.body?.size ?: 0)) {
                            var item1 = rsList?.body?.get(i)?.RSTR_NM
                            for (j in 0 until (rsImgList?.body?.size ?: 0)) {
                                var item2 = rsImgList?.body?.get(j)?.RSTR_NM

                                if (item1.equals(item2)) {
                                    Log.d("lsy", "rsList 이름 : ${item1}")
                                    Log.d("lsy", "rsImgList 이름 : ${item2}")
                                    binding.recyclerView.adapter =
                                        MyAdapter(requireContext(), rsList?.body, rsImgList?.body)

                                    binding.recyclerView.addItemDecoration(
                                        DividerItemDecoration(
                                            requireContext(),
                                            LinearLayoutManager.VERTICAL
                                        )
                                    )

                                    continue
                                }
                            }
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<PageListModel2>, t: Throwable) {
                        Log.d("lsy", "fail")
                        call.cancel()
                    }
                })

                Log.d("lsy", "실행 여부 확인. userListCall.enqueue")
            }

            override fun onFailure(call: retrofit2.Call<PageListModel>, t: Throwable) {
                Log.d("lsy", "fail")
                call.cancel()
            }
        })
    }
}