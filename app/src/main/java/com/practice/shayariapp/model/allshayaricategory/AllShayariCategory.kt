package com.practice.shayariapp.model.allshayaricategory

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "AllShayariCategory")
@Parcelize
data class AllShayariCategory(
  @PrimaryKey val uid : Int ?= 0,
  val name : String ?= null,
  val id : Int ?= null,
 ) : Parcelable

