package com.practice.shayariapp.viewmodel.allshayari

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.practice.shayariapp.db.ShayariDatabase
import com.practice.shayariapp.model.allshayaricategory.AllShayariCategory
import com.practice.shayariapp.repository.ShayariRepository
import kotlinx.coroutines.flow.Flow

class ShayariCategoryViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val shayariRepository : ShayariRepository
    val isLoading = MutableLiveData<Boolean>()

    init {
        val dao = ShayariDatabase.getDatabaseInstance(application).shayariDao()
         shayariRepository = ShayariRepository(dao)
     }

    fun getShayariCategory(): LiveData<List<AllShayariCategory>> {
        isLoading.value = true
        return shayariRepository.getAllShayariCategory().apply {
           isLoading.value = false
        }
    }
}