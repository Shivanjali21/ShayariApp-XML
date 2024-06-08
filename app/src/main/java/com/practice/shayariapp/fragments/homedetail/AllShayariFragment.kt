package com.practice.shayariapp.fragments.homedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.practice.shayariapp.R
import com.practice.shayariapp.databinding.FragmentAllShayariBinding
import com.practice.shayariapp.viewmodel.shayaricategory.AllShayariViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

class AllShayariFragment : Fragment(R.layout.fragment_all_shayari) {

    private val binding: FragmentAllShayariBinding by lazy {
        FragmentAllShayariBinding.inflate(layoutInflater)
    }

    private var receiveShayariCatId: Int = 0
    private val shayariContentAdapter by lazy { AllShayariAdapter(requireContext()) }
    private val argsReceive: AllShayariFragmentArgs by navArgs()
    private val allShayariViewModel: AllShayariViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment

        binding.apply {
            mtvShayariHeading.text = argsReceive.shayariData.name
            receiveShayariCatId = argsReceive.shayariData.id!!
           // Toast.makeText(requireContext(), "receive Id $receiveShayariCatId", Toast.LENGTH_SHORT).show()

            allShayariViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }

            allShayariViewModel.getAllShayariCategoryById(receiveShayariCatId)
                .onEach { shayariList ->
                    withContext(Dispatchers.Main) {
                        shayariContentAdapter.asyncDifferList.submitList(shayariList)
                    }
                }.launchIn(lifecycleScope)

            rvShayariContent.apply {
                adapter = shayariContentAdapter
            }

//            viewLifecycleOwner.lifecycleScope.launch {
//                repeatOnLifecycle(Lifecycle.State.STARTED) {
//                    try {
//                        val shayariList = allShayariViewModel.getById(receiveShayariCatId).first()
//                        withContext(Dispatchers.Main) {
//                            shayariContentAdapter.allShayariList = shayariList
//                            shayariContentAdapter.notifyDataSetChanged()
//                        }
//                    } catch (e: Exception) {
//                        // Handle error gracefully
//                    }
//                }
//            }
        }
        return binding.sDFragRoot
    }
}