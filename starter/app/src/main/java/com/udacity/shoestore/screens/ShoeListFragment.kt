package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ListItemShoeBinding
import com.udacity.shoestore.screens.main.MainViewModel

class ShoeListFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private var binding: FragmentShoeListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentShoeListBinding>(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        binding?.lifecycleOwner = this
        setHasOptionsMenu(true)

        observeShoeList()
        setupFloatingActionButton()
        return binding?.root
    }

    private fun setupFloatingActionButton() =
        binding?.floatingActionButton?.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

    private fun observeShoeList() =
        viewModel.shoeListViewData.observe(viewLifecycleOwner, Observer { shoeList ->
            shoeList.forEach {
                DataBindingUtil.inflate<ListItemShoeBinding>(
                    layoutInflater,
                    R.layout.list_item_shoe,
                    binding?.container,
                    true
                ).apply {
                    this.shoe = it
                }
            }
        })

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        NavigationUI.onNavDestinationSelected(
            item, requireView().findNavController()
        ) || super.onOptionsItemSelected(item)

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
