package com.example.teamproject.fragment

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
import com.example.teamproject.DetailActivity
import com.example.teamproject.R
import com.example.teamproject.databinding.Fragment1pBinding

class FragmentSecond : Fragment() {
    private var rstr_nm: String? = null
    private var rstr_img: String? = null
    private var rstr_addr: String? = null
    private var rstr_tell: String? = null
    private var rstr_intro: String? = null
    private var rstr_popularity: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_2p, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgBanner2 = view.findViewById<ImageView>(R.id.imgBanner2)
        val tvName2 = view.findViewById<TextView>(R.id.tvName2)

        rstr_nm = arguments?.getString("rstr_nm")
        rstr_img = arguments?.getString("rstr_img")
        rstr_addr = arguments?.getString("rstr_addr")
        rstr_tell = arguments?.getString("rstr_tell")
        rstr_intro = arguments?.getString("rstr_intro")
        rstr_popularity = arguments?.getString("rstr_popularity")

        Log.d("fraglist2", "$rstr_img, $rstr_nm")
        tvName2.text = rstr_nm

        if (rstr_img != null) {
            Glide.with(requireContext())
                .load(rstr_img)
                .into(imgBanner2)
        }
        imgBanner2.setOnClickListener {
            val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra("rstr_nm", rstr_nm)
                putExtra("rstr_img", rstr_img)
                putExtra("rstr_addr", rstr_addr)
                putExtra("rstr_tell", rstr_tell)
                putExtra("rstr_intro", rstr_intro)
                putExtra("rstr_popularity", rstr_popularity)
            }
            startActivity(intent)
        }
    }
}