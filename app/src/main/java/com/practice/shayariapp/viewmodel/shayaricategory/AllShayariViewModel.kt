package com.practice.shayariapp.viewmodel.shayaricategory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.practice.shayariapp.db.ShayariDatabase
import com.practice.shayariapp.model.allshayari.AllShayari
import com.practice.shayariapp.repository.ShayariRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AllShayariViewModel(application: Application) : AndroidViewModel(application) {

    private val shayariRepository : ShayariRepository
    val isLoading = MutableLiveData<Boolean>()

    init {
         val dao = ShayariDatabase.getDatabaseInstance(application).shayariDao()
         shayariRepository = ShayariRepository(dao)
     }

    fun getAllShayariCategoryById(catId: Int) : Flow<List<AllShayari>> {
       return flow {
           isLoading.value = true
          try {
            val shayariList = shayariRepository.getShayariByCate(catId)
            emit(shayariList)
            isLoading.value = false
          } catch (e: Exception) {
            // Handle error gracefully, e.g., log, emit empty list, or show error message
            emit(listOf()) // Example error handling
          }
      }
    }
}