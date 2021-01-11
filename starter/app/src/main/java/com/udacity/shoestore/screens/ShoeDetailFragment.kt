package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

        setupButtons(binding)

        return binding.root
    }

    private fun setupButtons(binding: FragmentShoeDetailBinding) {
        binding.buttonSave.setOnClickListener {
            viewModel.addShoeToList(
                name = binding.editTextName.text.toString(),
                company = binding.editTextCompany.text.toString(),
                size = binding.editTextSize.text.toString(),
                description = binding.editTextDescription.text.toString()
            )

            navigateToShoeList()
        }

        binding.buttonCancel.setOnClickListener {
            navigateToShoeList()
        }
    }

    private fun navigateToShoeList() =
        findNavController().navigate(
            ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment()
        )
}
