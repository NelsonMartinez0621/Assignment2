package com.example.assignment2.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.assignment2.data.model.BreweryItem
import com.example.assignment2.databinding.FragmentSearchBinding
import com.example.assignment2.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel:SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)



        viewModel.breweryItem.observe(viewLifecycleOwner) {
            when(it) {
                is NetworkResult.Loading -> {
                    Toast.makeText(context, "Loading. . .!", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Success -> {
                    setupSearchView()
                    fillCard(it.data)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }

    fun fillCard(data: BreweryItem?) {
        binding.apply {
            tvName.text = data?.name
            tvAddress.text = data?.address2
            tvBreweryType.text = data?.breweryType
            tvPhone.text = data?.phone
            tvWebsite.text = data?.websiteUrl
        }
    }

    fun setupSearchView() {
        viewModel.setupSearchView(binding.searchView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}