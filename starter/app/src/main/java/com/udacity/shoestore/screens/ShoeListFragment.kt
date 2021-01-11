package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ListItemShoeBinding
import com.udacity.shoestore.screens.main.MainViewModel


class ShoeListFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentShoeListBinding>(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        viewModel.shoeListViewData.observe(viewLifecycleOwner, Observer { shoeList ->
            shoeList.forEach {
                DataBindingUtil.inflate<ListItemShoeBinding>(
                    layoutInflater,
                    R.layout.list_item_shoe,
                    binding.container,
                    true
                ).apply {
                    this.shoe = it
                }
            }
        })

        return binding.root
    }
}
