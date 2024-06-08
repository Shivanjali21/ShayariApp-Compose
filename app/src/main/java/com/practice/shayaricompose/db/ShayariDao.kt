package com.practice.shayaricompose.db

import androidx.room.Dao
import androidx.room.Query
import com.practice.shayaricompose.models.allshayari.AllShayari
import com.practice.shayaricompose.models.shayaricategory.ShayariCategory

@Dao
interface ShayariDao {

    @Query("SELECT * FROM AllShayariCategory ORDER By uid ASC")
    fun getShayariCategory() : List<ShayariCategory>

    @Query("SELECT * FROM AllShayari WHERE Cat_id = :catId")
    fun getAllShayari(catId:Int) : List<AllShayari>
}