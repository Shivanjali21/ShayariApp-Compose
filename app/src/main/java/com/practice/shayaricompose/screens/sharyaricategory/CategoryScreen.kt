package com.practice.shayaricompose.screens.sharyaricategory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.practice.shayaricompose.R
import com.practice.shayaricompose.ui.theme.LightColorScheme
import com.practice.shayaricompose.viewmodels.ShayariViewModel

@Composable
fun CategoryScreen(navController: NavController, viewModel: ShayariViewModel) {
    /*Text(text = "Cate Screen", modifier = Modifier.clickable {
       navController.navigate("ShayariScreen")
    })*/
    Scaffold(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        topBar =  {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "All Shayari",
                    style = TextStyle(
                        fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.mochiypop)),
                        color = Color(LightColorScheme.primary.toArgb()),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }) {
        LazyColumn(modifier = Modifier
            .padding(it).padding(top = 10.dp)) {
            items(viewModel.getShayariCategory()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            brush = Brush.linearGradient(
                                listOf(Color(0xFF990707), Color(0XFFECE8D8))
                            )
                        )
                )
                {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp).clickable {
                           navController.navigate("ShayariScreen" + "/${it.id}" + "/${it.name}")
                        },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(id = R.drawable.shayari_icon),
                            contentDescription = null,
                            modifier = Modifier.height(40.dp))
                        Text(
                            text = it.name.toString(),
                            fontWeight = FontWeight(600),
                            style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.mochiypop)), color = Color.White)
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                            contentDescription = null,
                            tint = LightColorScheme.primary
                        )
                    }
                }
            }
        }
    }
}