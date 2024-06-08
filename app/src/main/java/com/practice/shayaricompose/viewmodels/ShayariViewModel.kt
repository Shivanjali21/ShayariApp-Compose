package com.practice.shayaricompose.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.practice.shayaricompose.db.ShayariDatabase
import com.practice.shayaricompose.models.allshayari.AllShayari
import com.practice.shayaricompose.models.shayaricategory.ShayariCategory
import com.practice.shayaricompose.repository.ShayariRepository

class ShayariViewModel (application: Application) : AndroidViewModel(application) {

    private val shayariRepository : ShayariRepository
    val isLoading = MutableLiveData<Boolean>()

    init {
       val shayariCategoryDao = ShayariDatabase.getDatabaseInstance(application).shayariDao()
       shayariRepository = ShayariRepository(shayariCategoryDao)
    }

    fun getShayariCategory() : List<ShayariCategory> {
       isLoading.value = true
       return shayariRepository.getShayariCategory().also {
          isLoading.value = false
       }
    }

    fun getShayariByCategoryId(catId:Int) : List<AllShayari> {
        isLoading.value = true
        return shayariRepository.getAllShayari(catId).also {
            isLoading.value = false
        }
    }
}