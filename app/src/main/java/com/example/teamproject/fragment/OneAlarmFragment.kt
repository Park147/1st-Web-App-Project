package com.example.teamproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.teamproject.databinding.FragmentOneAlarmBinding

class OneAlarmFragment : Fragment() {
    lateinit var binding: FragmentOneAlarmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneAlarmBinding.inflate(inflater, container, false)
        return binding.root
    }

}