package com.example.assignment2.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.data.model.Brewery
import com.example.assignment2.databinding.FragmentListBinding
import com.example.assignment2.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreweryListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //delagates
    private val viewModel: BreweryListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)


        binding.let {
            viewModel.breweryList.observe(viewLifecycleOwner) {
                when(it) {
                    is NetworkResult.Loading -> {
                        Toast.makeText(context, "Loading. . .!", Toast.LENGTH_SHORT).show()
                    }
                    is NetworkResult.Success -> {
                        initView(it.data)
                    }
                    is NetworkResult.Error -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            viewModel.getBreweryList()
        }
        return binding.root
    }

    private fun initView(data: Brewery?) {
        data?.let {
            binding.rvBreweryList.layoutManager = LinearLayoutManager(context)
            binding.rvBreweryList.adapter = BreweryListAdapter(data)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}