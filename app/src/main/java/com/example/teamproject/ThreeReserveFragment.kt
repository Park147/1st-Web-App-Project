package com.example.teamproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teamproject.databinding.FragmentThreeReserveBinding


class ThreeReserveFragment : Fragment() {
    lateinit var binding: FragmentThreeReserveBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThreeReserveBinding.inflate(inflater, container, false)
        return binding.root
    }

}