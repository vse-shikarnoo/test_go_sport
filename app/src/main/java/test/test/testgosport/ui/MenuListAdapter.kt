package test.test.testgosport.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import test.test.testgosport.R
import test.test.testgosport.databinding.ItemMenuListLayoutBinding
import test.test.testgosport.model.MealInfo

class MenuListAdapter(
    private val onItemClick: (position: Int) -> Unit
) : ListAdapter<MealInfo, MenuListAdapter.Holder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemMenuListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<MealInfo>() {
        override fun areItemsTheSame(oldItem: MealInfo, newItem: MealInfo): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(
            oldItem: MealInfo,
            newItem: MealInfo
        ): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(
        private val binding: ItemMenuListLayoutBinding,
        val onItemClick: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        fun bind(meal: MealInfo) {
            with(binding) {
                titleTextView.text = meal.strMeal
                var ingredientsString = ""
                val ingredientsSet = meal.getIngredients().keys

                for (i in ingredientsSet) {
                    if (i.isNotBlank()) {
                        if (ingredientsString.length + i.length < 70) {
                            ingredientsString += i.toLowerCase()
                            ingredientsString += ", "
                        } else {
                            ingredientsString += "....."
                            break
                        }
                    }
                }
//                meal.getIngredients().keys.forEach {
//                    if (it.isNotBlank()) {
//                        ingredientsString += it.toLowerCase()
//                        ingredientsString += ", "
//                    }
//                }
                //ingredTextView.text = ingredientsString.dropLast(2)
                //ingredTextView.text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"//70
//                ingredientsString = if (ingredientsString.length > 70) {
//                    ingredientsString.substring(0, 70) + "..."
//                } else {
//                    ingredientsString.dropLast(2)
//                }
                ingredientsString = ingredientsString.dropLast(2)
                ingredientsString = ingredientsString.replaceFirstChar { it.uppercase() }
                ingredTextView.text = ingredientsString
                Glide.with(itemView)
                    .load(meal.strMealThumb)
                    .error(itemView.context.getDrawable(R.drawable.baseline_error_24))
                    .into(imageView)
            }
        }
    }
}