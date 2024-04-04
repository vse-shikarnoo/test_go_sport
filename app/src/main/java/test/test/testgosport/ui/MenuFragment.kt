package test.test.testgosport.ui

import android.os.Bundle
import android.view.View
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
    private var listAdapter : MenuListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMeals()

        initAdapter()
        observe()
        listeners()
    }

    private fun initAdapter() {
        listAdapter = MenuListAdapter {

        }
        with(binding.menuList){
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        TODO("Заменить на ViewPager")
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
        }
        viewModel.errorToast.observe(viewLifecycleOwner) {
            with(binding) {
                progressBar.visibility = View.GONE
                rertyButton.visibility = View.VISIBLE
            }
            toast("Error")
        }
    }
}