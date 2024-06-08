package com.practice.shayaricompose.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practice.shayaricompose.screens.allshayari.ShayariScreen
import com.practice.shayaricompose.screens.sharyaricategory.CategoryScreen
import com.practice.shayaricompose.ui.theme.ShayariComposeTheme
import com.practice.shayaricompose.viewmodels.ShayariViewModel
import timber.log.Timber

class MainActivity : ComponentActivity() {
    private lateinit var viewModel : ShayariViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ShayariViewModel::class.java]
        setContent {
            ShayariComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   ShayariApp(paddingValues = innerPadding, viewModel)
                }
            }
        }
    }
}

@Composable
fun ShayariApp(paddingValues: PaddingValues, viewModel: ShayariViewModel) {
   Box(modifier = Modifier.padding(paddingValues)){
      val navController = rememberNavController()
      NavHost(navController = navController, startDestination = "CategoryScreen" ) {
         composable(route = "CategoryScreen") {
           CategoryScreen(navController,viewModel)
         }
         composable(route = "ShayariScreen"+"/{id}"+"/{name}") {
           val id = it.arguments?.getString("id")
           val name = it.arguments?.getString("name")
           Timber.d("MainActivity", "Shayari Screen Navi: $id")
           ShayariScreen(cateName = name.toString(),viewModel.getShayariByCategoryId(id!!.toInt()))
         }
      }
   }
}
