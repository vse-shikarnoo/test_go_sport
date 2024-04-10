package test.test.testgosport.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
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
        holder.bind(getItem(position), position)
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
                clearClickList(adapterPosition)
                var k = 0
                for (i in clickList) {
                    if (i) {
                        k++
                    }
                }
                Log.d("TestCat", clickList.toString())
                Log.d("TestCat", k.toString())
                if (k == 0) {
                    clearCallback()
                }
            }
        }

        private fun clearClickList(position: Int) {
            for (i in clickList.indices) {
                if (i != position)
                    clickList[i] = false
            }
        }

        fun bind(category: CategoryInfo, position: Int) {
            with(binding) {
                categoryTextView.text = category.strCategory
                //Log.d("TestCat", position.toString())
                //Log.d("TestCat", clickList[position].toString())
                Log.e("bind cat view", "problem with changing view")
                horLine.visibility = if (category.flag) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
        }
    }
}