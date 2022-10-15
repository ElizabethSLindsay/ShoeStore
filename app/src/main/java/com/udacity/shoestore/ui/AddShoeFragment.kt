package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

        binding.shoe = Shoe("",0.0,"","", emptyList())

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_addShoeFragment_to_shoeListFragment)
        }

        binding.saveButton.setOnClickListener {
            saveNewShoe()
        }

        return binding.root
    }

    private fun saveNewShoe() {
        val shoe = Shoe(
            name = binding.shoe!!.name,
            size = binding.shoe!!.size,
            company = binding.shoe!!.company,
            description = binding.shoe!!.description
        )

        viewModel.addShoe(shoe)

        findNavController().navigate(R.id.action_addShoeFragment_to_shoeListFragment)
    }

}
