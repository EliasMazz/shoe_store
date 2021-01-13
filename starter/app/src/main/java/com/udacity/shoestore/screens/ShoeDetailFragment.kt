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
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.screens.main.MainViewModel

class ShoeDetailFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding = DataBindingUtil.inflate<FragmentShoeDetailBinding>(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )

        binding.viewModel = viewModel

        viewModel.eventNavigate.observe(viewLifecycleOwner, Observer {
            if (it) {
                navigateToShoeList()
                viewModel.onNavigateComplete()
            }
        })

        return binding.root
    }

    private fun navigateToShoeList() =
        findNavController().navigate(
            ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment()
        )
}
