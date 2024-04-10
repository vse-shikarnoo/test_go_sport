package test.test.testgosport.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import test.test.testgosport.R
import test.test.testgosport.databinding.ItemMenuListLayoutBinding
import test.test.testgosport.databinding.ItemNewsListLayoutBinding
import test.test.testgosport.model.MealInfo

class NewsListAdapter(
    private val onItemClick: (position: Int) -> Unit
) : ListAdapter<Int, NewsListAdapter.Holder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemNewsListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Int,
            newItem: Int
        ): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(
        private val binding: ItemNewsListLayoutBinding,
        val onItemClick: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        fun bind(drawable: Int) {
            with(binding) {
                imageView.setImageResource(drawable)
            }
        }
    }
}