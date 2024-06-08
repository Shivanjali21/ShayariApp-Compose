package com.practice.shayaricompose.models.shayaricategory

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AllShayariCategory")
data class ShayariCategory(
   @PrimaryKey @ColumnInfo(name = "uid" ) val uid :Int ?= 0,
   @ColumnInfo(name = "name") val name :String ?= null,
   @ColumnInfo(name = "id") val id :Int ?= 0)
