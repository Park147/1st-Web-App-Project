package com.example.teamproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.teamproject.databinding.FragmentTwoAlarmBinding

class TwoAlarmFragment : Fragment() {
   lateinit var binding: FragmentTwoAlarmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoAlarmBinding.inflate(inflater, container, false)
        return binding.root
    }

}