package com.practice.shayariapp.model.allshayari

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "AllShayari")
@Parcelize
data class AllShayari(
  @PrimaryKey val uid : Int ?= 0,
  @ColumnInfo(name = "Cat_id") val Cat_id : Int ?= 0,
  val Shayari : String ?= null,
) : Parcelable