package com.practice.shayaricompose.repository

import com.practice.shayaricompose.db.ShayariDao
import com.practice.shayaricompose.models.allshayari.AllShayari
import com.practice.shayaricompose.models.shayaricategory.ShayariCategory

class ShayariRepository (private val shayariDao: ShayariDao) {

   fun getShayariCategory() : List<ShayariCategory> {
      return shayariDao.getShayariCategory()
   }

   fun getAllShayari(catId : Int) : List<AllShayari> {
      return shayariDao.getAllShayari(catId)
   }
}