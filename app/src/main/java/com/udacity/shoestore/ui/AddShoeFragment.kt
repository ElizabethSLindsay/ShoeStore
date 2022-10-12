package com.udacity.shoestore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentAddShoeBinding

import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.ShoeViewModel



class AddShoeFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()
    private var _binding: FragmentAddShoeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddShoeBinding.inflate(inflater, container, false)

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_addShoeFragment_to_shoeListFragment)
        }

        binding.saveButton.setOnClickListener {
            saveNewShoe()
        }

        return binding.root
    }

    private fun saveNewShoe() {
        val newShoe = Shoe(
            name = binding.editTextShoeName.text.toString(),
            size = binding.editTextNumberDecimalShoeSize.text.toString().toDouble(),
            company = binding.editTextBrandName.text.toString(),
            description = binding.editTextDescription.text.toString()
        )

        viewModel.addShoe(newShoe)

        findNavController().navigate(R.id.action_addShoeFragment_to_shoeListFragment)
    }
}
