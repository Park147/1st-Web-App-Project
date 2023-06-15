package com.example.a1st_web_app_project.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.a1st_web_app_project.R

class FragmentThird() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_3p, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fragment에서 사용할 ImageView와 TextView 등의 요소들을 findViewById를 통해 초기화하고 작업을 수행할 수 있습니다.
        val imgBanner1 = view.findViewById<ImageView>(R.id.imgBanner3)
        val tvName1 = view.findViewById<TextView>(R.id.tvName3)

        val rstr_nm = arguments?.getString("rstr_nm")
        val rstr_img = arguments?.getString("rstr_img")

        Log.d("fraglist3", "${rstr_img}, ${rstr_nm}")
        tvName1.text = rstr_nm

        if (rstr_img != null) {
            Glide.with(requireContext())
                .load(rstr_img)
                .into(imgBanner1)
        }
        // 여기에서 ImageView나 TextView 등의 요소에 원하는 값이나 동작을 설정할 수 있습니다.
        // 예를 들면, imgBanner1.setImageResource(R.drawable.your_image_resource) 등을 사용하여 이미지 설정 등을 할 수 있습니다.
    }
}