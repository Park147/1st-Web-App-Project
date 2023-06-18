package com.example.a1st_web_app_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a1st_web_app_project.databinding.FragmentContainerBinding

class FilterFragment : Fragment() {
    private var _binding: FragmentContainerBinding? = null
    private val binding get() = _binding!!

    private lateinit var filterListener: FilterListener

    interface FilterListener {
        fun onFilterSelected(filter: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFilter1.setOnClickListener {
            filterListener.onFilterSelected("중구")
        }

        binding.buttonFilter2.setOnClickListener {
            filterListener.onFilterSelected("종로구")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}