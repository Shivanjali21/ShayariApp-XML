package com.practice.shayariapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.practice.shayariapp.model.allshayari.AllShayari
import com.practice.shayariapp.model.allshayaricategory.AllShayariCategory

@Dao
interface ShayariDao {

    @Query("SELECT * FROM AllShayariCategory ORDER BY uid ASC")
    fun getAllShayariCData() : LiveData<List<AllShayariCategory>>

    @Query("SELECT * FROM AllShayari WHERE Cat_id = :Cat_id")
    suspend fun getAllShayariData(Cat_id:Int) : List<AllShayari>
}