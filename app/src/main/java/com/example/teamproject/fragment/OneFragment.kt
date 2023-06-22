package com.example.teamproject.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.teamproject.MyApplication
import com.example.teamproject.databinding.FragmentOneBinding
import com.example.teamproject.databinding.FragmentOneWaitingBinding
import com.example.teamproject.databinding.FragmentThreeWaitingBinding
import com.example.teamproject.databinding.FragmentTwoWaitingBinding
import com.example.teamproject.model.ItemDataList
import com.example.teamproject.recycler.MyWaitingAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OneFragment : Fragment() {

    lateinit var binding: FragmentOneBinding
    lateinit var adapter: MyWaitingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)


        val tabLayout = binding.ReserveTabs

        val viewPager = binding.ReserveViewpager

        viewPager.adapter= MyWaitingFragmentPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "방문예정"
                }

                1 -> {
                    tab.text = "방문완료"
                }

                2 -> {
                    tab.text = "취소/노쇼"
                }
            }
        }.attach()

        return binding.root
    }
    class MyWaitingFragmentPagerAdapter(Fragment: Fragment): FragmentStateAdapter(Fragment){
        val fragments: List<Fragment>
        init {
            fragments= listOf(OneWaitingFragment(), TwoWaitingFragment(), ThreeWaitingFragment())
        }
        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]

    }
}