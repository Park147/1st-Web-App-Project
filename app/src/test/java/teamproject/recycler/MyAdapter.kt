import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teamproject.MyApplication
import com.example.teamproject.R
import com.example.teamproject.databinding.ItemMainBinding
import com.example.teamproject.login.LoginActivity
import com.example.teamproject.model.Bookmark
import com.example.teamproject.model.RstrModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface MyAdapterListener {
    fun onItemClick(data: RstrModel)
}

class MyViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter(val context: Context, var datas: List<RstrModel>?) :
    RecyclerView.Adapter<MyViewHolder>() {

    private var listener: MyAdapterListener? = null

    fun setListener(listener: MyAdapterListener) {
        this.listener = listener
    }

    fun updateDatas(updaters: List<RstrModel>) {
        datas = updaters
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = holder.binding

        val rstrModel = datas?.get(position)
        binding.id.text = rstrModel?.rstr_nm
        binding.firstNameView.text = "주소: ${rstrModel?.rstr_addr}"
        binding.contactView1.text = "전화: ${rstrModel?.rstr_tell}"
        binding.contactView2.text = "업종: ${rstrModel?.rstr_list}"
        binding.contactView3.text = "소개: ${rstrModel?.rstr_intro}"
        binding.contactView4.text = "평점: ${rstrModel?.rstr_popularity}"
        binding.bookmarkbtn.contentDescription = rstrModel?.rstr_nm

        val loginSharedPref = context.getSharedPreferences("login_prof", Context.MODE_PRIVATE)
        val userId = loginSharedPref.getString("m_id", null)

        val imageUrl = rstrModel?.rstr_img

        if (imageUrl != null) {
            Glide.with(context)
                .load(imageUrl)
                .into(binding.avatarView)
        }

        val userService = ( context.applicationContext as MyApplication).userService

        val bookck = userService.bookcheck(userId.toString(), rstrModel?.rstr_nm.toString())

        bookck.enqueue(object: Callback<Int>{
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful) {
                    val booknum = response.body()
                    if (booknum == 0) {
                        Log.d("markviewtest2", "성공! ${booknum}")
                        binding.bookmarkbtn.setImageResource(R.drawable.bookmarkoff)
                        binding.bookmarkbtn.setTag(R.string.image_resource_name, "bookmarkoff")
                    } else if (booknum == 1) {
                        Log.d("markviewtest3", "실패! ${booknum}")
                        binding.bookmarkbtn.setImageResource(R.drawable.bookmarkon)
                        binding.bookmarkbtn.setTag(R.string.image_resource_name, "bookmarkon")
                    }
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                call.cancel()
            }

        })

        binding.bookmarkbtn.setOnClickListener {
            if ( userId == null)
            {
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
            }
            else {
                val userService = ( context.applicationContext as MyApplication).userService
                val b_name = binding.bookmarkbtn.contentDescription as String
                val imgd = binding.bookmarkbtn.getTag(R.string.image_resource_name) as String?
                Log.d("imgbtn","이미지 이름: ${imgd}, url: ${rstrModel?.rstr_img}")
                Toast.makeText(context, "식당 이름: ${b_name}, 이미지 이름: ${imgd}", Toast.LENGTH_SHORT).show()
                if ( imgd == "bookmarkon")
                {
                    val delbook = userService.bmdelete(userId, b_name)
                    delbook.enqueue(object: Callback<Unit>{
                        override fun onResponse(
                            call: Call<Unit>,
                            response: Response<Unit>
                        ) {
                            if (response.isSuccessful)
                            {
                                binding.bookmarkbtn.setImageResource(R.drawable.bookmarkoff)
                                binding.bookmarkbtn.setTag(R.string.image_resource_name,"bookmarkoff")
                                Log.d("bookmarkup", "성공!")
                            }
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            Log.d("bookmarkd", "실패 ${t.message}")
                            call.cancel()
                        }

                    })
                } else if ( imgd == "bookmarkoff")
                {
                    var bookmark = Bookmark(
                        b_id = userId.toString(),
                        b_name = b_name!!.toString(),
                        b_imgurl = rstrModel?.rstr_img.toString()
                    )
                    Log.d("bookmarkup", "${bookmark}")
                    val addbook = userService.bmregister(bookmark)
                    addbook.enqueue(object: Callback<Unit>{
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            if (response.isSuccessful)
                            {
                                binding.bookmarkbtn.setImageResource(R.drawable.bookmarkon)
                                binding.bookmarkbtn.setTag(R.string.image_resource_name, "bookmarkon")
                                Log.d("bookmarkup", "성공!")
                            }
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            Log.d("bookmarka", "실패 ${t.message}")
                            call.cancel()
                        }

                    })
                }

            }

        }

        binding.root.setOnClickListener {
            if (rstrModel != null) {
                listener?.onItemClick(rstrModel)
            }
        }
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }
}