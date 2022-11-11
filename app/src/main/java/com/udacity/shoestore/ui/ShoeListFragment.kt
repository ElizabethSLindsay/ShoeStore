package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.ShoeViewModel
import timber.log.Timber


class ShoeListFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()

    private var _binding: FragmentShoeListBinding? = null
    private val binding get() = _binding!!

    private lateinit var shoeBinding: ShoeItemBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoeListBinding.inflate(inflater, container, false)

        viewModel.shoeList.observe(viewLifecycleOwner) {
            Timber.d(viewModel.shoeList.value.toString())
            binding.shoeList.removeAllViews()
            for (shoe in it) {
                addNewShoe(shoe)
            }
        }

        binding.addShoeBtn.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_addShoeFragment)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun addNewShoe(shoe: Shoe) {
        shoeBinding = ShoeItemBinding.inflate(layoutInflater)
        shoeBinding.shoeNameCard.text = getString(R.string.shoe_name_card, shoe.name)
        shoeBinding.shoeSizeCard.text = getString(R.string.shoe_size_card, shoe.size.toString())
        shoeBinding.companyCard.text = getString(R.string.company_card, shoe.company)
        shoeBinding.descriptionCard.text = getString(R.string.description_card, shoe.description)

        binding.shoeList.addView(shoeBinding.shoeCard)
        Timber.d(binding.shoeList.childCount.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_shoe_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.logoutMenuItem ->
                findNavController().navigate(R.id.action_shoeListFragment_to_loginFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}