package com.practice.shayariapp.repository

import androidx.lifecycle.LiveData
import com.practice.shayariapp.db.ShayariDao
import com.practice.shayariapp.model.allshayari.AllShayari
import com.practice.shayariapp.model.allshayaricategory.AllShayariCategory

class ShayariRepository (private val dao: ShayariDao)  {

   fun getAllShayariCategory(): LiveData<List<AllShayariCategory>> {
      return dao.getAllShayariCData()
   }

   suspend fun getShayariByCate(cateId: Int) : List<AllShayari>  {
     return dao.getAllShayariData(cateId)
   }
}