import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teamproject.databinding.ItemMainBinding
import com.example.teamproject.model.RstrModel

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

        val imageUrl = rstrModel?.rstr_img

        if (imageUrl != null) {
            Glide.with(context)
                .load(imageUrl)
                .into(binding.avatarView)
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