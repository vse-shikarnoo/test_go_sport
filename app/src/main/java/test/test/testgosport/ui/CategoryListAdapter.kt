package test.test.testgosport.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import test.test.testgosport.R
import test.test.testgosport.databinding.ItemCatListLayoutBinding
import test.test.testgosport.databinding.ItemMenuListLayoutBinding
import test.test.testgosport.model.CategoryInfo
import test.test.testgosport.model.MealInfo

class CategoryListAdapter(
    private val onItemClick: (position: Int) -> Unit,
    private val clearCallback: () -> Unit
) : ListAdapter<CategoryInfo, CategoryListAdapter.Holder>(DiffUtilCallback()) {

    private val clickList = mutableListOf<Boolean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemCatListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, onItemClick, clearCallback, clickList)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setClickList(size: Int) {
        for (i in 0..size) {
            clickList.add(false)
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<CategoryInfo>() {
        override fun areItemsTheSame(oldItem: CategoryInfo, newItem: CategoryInfo): Boolean {
            return oldItem.strCategory == newItem.strCategory
        }

        override fun areContentsTheSame(
            oldItem: CategoryInfo,
            newItem: CategoryInfo
        ): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(
        private val binding: ItemCatListLayoutBinding,
        val onItemClick: (position: Int) -> Unit,
        val clearCallback: () -> Unit,
        private val clickList: MutableList<Boolean>
    ) : RecyclerView.ViewHolder(binding.root) {


        init {
            binding.root.setOnClickListener {
                onItemClick(adapterPosition)
                clickList[adapterPosition] = !clickList[adapterPosition]
                if (clickList[adapterPosition]) {
                    binding.root.setBackgroundColor(itemView.context.getColor(R.color.black))
                }else{
                    binding.root.setBackgroundColor(itemView.context.getColor(R.color.white))
                }
                TODO("Проблема с clearcallback")
                var k =0
                for (i in clickList){
                    if (i==true){
                        k++
                    }
                }
                if (k==0){
                    clearCallback
                }
            }
        }

        fun bind(category: CategoryInfo) {
            with(binding) {
                binding.categoryTextView.text = category.strCategory
            }

        }
    }
}