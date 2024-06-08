package com.practice.shayariapp.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.practice.shayariapp.R
import com.practice.shayariapp.databinding.FragmentAllShayariCategoryBinding
import com.practice.shayariapp.model.allshayaricategory.AllShayariCategory
import com.practice.shayariapp.viewmodel.allshayari.ShayariCategoryViewModel
import kotlinx.coroutines.launch

class AllShayariCategoryFragment : Fragment(R.layout.fragment_all_shayari_category) {

    private val binding : FragmentAllShayariCategoryBinding by lazy {
        FragmentAllShayariCategoryBinding.inflate(layoutInflater)
    }
    private val shayariViewModel : ShayariCategoryViewModel by viewModels()
    private val shayariAdapter : AllShayariCategoryAdapter by lazy {
       AllShayariCategoryAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment

        binding.apply {
            rvShayari.apply {
                adapter = shayariAdapter

                shayariViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                  progressBar.visibility = if(isLoading)  View.VISIBLE else View.GONE
                }

                shayariAdapter.onItemClick = {
                    val sData = AllShayariCategory(it.uid , name = it.name, id =  it.id)
                    val action = AllShayariCategoryFragmentDirections.actionAllShayariCategoryFragmentToAllShayariFragment(sData)
                    findNavController().navigate(action)
                }

                viewLifecycleOwner.lifecycleScope.launch {
                   shayariViewModel.getShayariCategory().observe(viewLifecycleOwner){
                      shayariAdapter.asyncListDiffer.submitList(it)
                   }
                }
            }
            return binding.homeFragRoot
        }
    }
}