package com.example.teamproject.login

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.teamproject.MyApplication
import com.example.teamproject.R
import com.example.teamproject.databinding.ActivityModifyProfileBinding
import com.example.teamproject.model.ModInfo
import com.example.teamproject.model.Pimg
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream


class ModifyProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityModifyProfileBinding
    lateinit var filePath: String

    var modprofiler: Button? = null
    var proimg: ImageButton? = null
    var savebtn: Button? = null
    var savebitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.title = "프로필 수정"

        // 이전 페이지에서 intent를 통해서 받아온 값을 출력하는 부분
        var m_id = intent.getStringExtra("m_id")
        var m_nickname = intent.getStringExtra("m_nickname")
        var m_introduction = intent.getStringExtra("m_introduction")
        var m_activity_area = intent.getStringExtra("m_activity_area")

        binding.modnick.setText(m_nickname)
        binding.modintro.setText(m_introduction)
        binding.modmove.setText(m_activity_area)
        modprofiler = binding.modprofiler
        proimg = binding.proimg
        savebtn = binding.savebtn

        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            try {
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize)
                )
                val option = BitmapFactory.Options()
                //
                option.inSampleSize = calRatio

                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)

                inputStream!!.close()
                inputStream = null

                bitmap?.let {
                    binding.proimg.setImageBitmap(bitmap)

//                    val baos = ByteArrayOutputStream()
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, baos)
//                    val bytes = baos.toByteArray()
//                    val mapImage_String: String = Base64.encodeToString(bytes, Base64.DEFAULT)
//
//                    binding.savebtn.setOnClickListener {
//                        var pimg = Pimg(
//                            idx = m_id.toString(),
//                            img = mapImage_String
//                        )
//
//                        val userService = (applicationContext as MyApplication).userService
//
//                        val userimg = userService.imgsave(pimg)
//                        userimg.enqueue(object: Callback<Unit>{
//                            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
//                                if (response.isSuccessful){
//                                    Log.d("galtest2","성공")
//                                }
//                            }
//
//                            override fun onFailure(call: Call<Unit>, t: Throwable) {
//                                Log.d("galtest3", "실패 ${t.message}")
//                                call.cancel()
//                            }
//
//
//                        })

//                    }

                } ?: let{
                    Log.d("kkang", "bitmap null")
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }



        binding.proimg.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }

        // 프로필을 수정하는 구간
        binding.modprofiler.setOnClickListener {
            var member = ModInfo(
                m_id = m_id.toString(),
                m_nickname = binding.modnick.text.toString(),
                m_introduction = binding.modintro.text.toString(),
                m_activity_area = binding.modmove.text.toString()
            )

            Log.d("Modprofiler1", "성공 ${member}")

            val userService = (applicationContext as MyApplication).userService

            var modprofiler = userService.mUpIntro(member)
            Log.d("Modprofiler2", "성공 ${modprofiler}")

            modprofiler.enqueue(object: Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful){
                        var modpromemb = response.body()

                        Log.d("Modprofiler3", "성공 ${modpromemb}")
                        //이전 페이지로 돌아가는 onBackPressed() 함수
                        onBackPressed()
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("Modprofiler4", "실패 ${t.message}")
                    call.cancel()
                }

            })
        }



    }

//    fun bitmapToByteArray(bitmap: Bitmap): String? {
//        var image: String? = ""
//        val stream = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
//        val byteArray = stream.toByteArray()
//        image = byteArrayToBinaryString(byteArray)
//        Log.d("selimgsb2", "${byteArray}")
//        return image
//    }
//
//    /**바이너리 바이트 배열을 스트링으로 바꾸어주는 메서드  */
//    fun byteArrayToBinaryString(b: ByteArray): String? {
//        val sb = StringBuilder()
//        for (i in b.indices) {
//            sb.append(byteToBinaryString(b[i]))
//        }
//        Log.d("selimgsb1", "${sb}")
//        return sb.toString()
//    }
//
//    /**바이너리 바이트를 스트링으로 바꾸어주는 메서드  */
//    fun byteToBinaryString(n: Byte): String? {
//        val sb = StringBuilder("00000000")
//        for (bit in 0..7) {
//            if (n.toInt() shr bit and 1 > 0) {
//                sb.setCharAt(7 - bit, '1')
//            }
//        }
//
//        return sb.toString()
//    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try {
            var inputStream = contentResolver.openInputStream(fileUri)
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            // 반으로 줄이기.
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}