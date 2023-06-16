package com.example.a1st_web_app_project.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.a1st_web_app_project.DetailActivity
import com.example.a1st_web_app_project.R
import com.example.a1st_web_app_project.databinding.Fragment1pBinding

class FragmentSecond : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_2p, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgBanner2 = view.findViewById<ImageView>(R.id.imgBanner2)
        val tvName2 = view.findViewById<TextView>(R.id.tvName2)

        val rstr_nm = arguments?.getString("rstr_nm")
        val rstr_img = arguments?.getString("rstr_img")

        Log.d("fraglist2", "$rstr_img, $rstr_nm")
        tvName2.text = rstr_nm

        if (rstr_img != null) {
            Glide.with(requireContext())
                .load(rstr_img)
                .into(imgBanner2)
        }

        imgBanner2.setOnClickListener {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            startActivity(intent)
        }
    }
}