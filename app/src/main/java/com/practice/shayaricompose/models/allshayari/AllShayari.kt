package com.practice.shayaricompose.models.allshayari

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AllShayari")
data class AllShayari(
    @PrimaryKey @ColumnInfo(name = "uid") val uid : Int ?= 0,
    @ColumnInfo(name = "Cat_id") val catId : Int ?= 0,
    @ColumnInfo(name = "Shayari") val shayari : String ?= null,
)
