package test.test.testgosport.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import test.test.testgosport.R
import test.test.testgosport.databinding.FragmentMenuLayoutBinding
import test.test.testgosport.utils.autoCleared
import test.test.testgosport.utils.toast
import test.test.testgosport.vm.MenuViewModel

class MenuFragment : Fragment(R.layout.fragment_menu_layout) {

    private val binding: FragmentMenuLayoutBinding by viewBinding(FragmentMenuLayoutBinding::bind)
    private val viewModel: MenuViewModel by viewModels()
    private var listAdapter: MenuListAdapter by autoCleared()
    private var catListAdapter: CategoryListAdapter by autoCleared()
    private var newsListAdapter: NewsListAdapter by autoCleared()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        initAdapter()
        observe()
        listeners()

        viewModel.getCategories()
        viewModel.getMeals()
        viewModel.getNews()
    }

    private fun initAdapter() {
        listAdapter = MenuListAdapter {
        }
        with(binding.menuList) {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        catListAdapter = CategoryListAdapter({
            viewModel.getSortedMeals(viewModel.categories.value!![it].strCategory)
            //catListAdapter.notifyDataSetChanged()
            viewModel.categories.value!![it].flag = !viewModel.categories.value!![it].flag
        }, {
            viewModel.getMeals()
            Log.d("ClearCallback", "clearCallback")
        })
        with(binding.catList) {
            adapter = catListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            //addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
        }

        newsListAdapter = NewsListAdapter { }
        with(binding.newsList) {
            adapter = newsListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun listeners() {
        binding.rertyButton.setOnClickListener {
            with(binding) {
                progressBar.visibility = View.VISIBLE
                rertyButton.visibility = View.GONE
            }
            viewModel.getMeals()
        }
    }

    private fun observe() {
        viewModel.meals.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
            binding.progressBar.visibility = View.GONE
            Log.d("ClearCallback", "list observe")
        }
        viewModel.errorToast.observe(viewLifecycleOwner) {
            with(binding) {
                progressBar.visibility = View.GONE
                rertyButton.visibility = View.VISIBLE
            }
            toast("Error")
        }
        viewModel.categories.observe(viewLifecycleOwner) {
            catListAdapter.setClickList(it.size)
            catListAdapter.submitList(it)
        }
        viewModel.sortedMeals.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }
        viewModel.news.observe(viewLifecycleOwner){
            newsListAdapter.submitList(it)
        }
    }
}