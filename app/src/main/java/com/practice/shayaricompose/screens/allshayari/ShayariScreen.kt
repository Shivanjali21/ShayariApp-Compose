package com.practice.shayaricompose.screens.allshayari

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Whatsapp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practice.shayaricompose.R
import com.practice.shayaricompose.models.allshayari.AllShayari
import com.practice.shayaricompose.ui.theme.LightColorScheme

@Composable
fun ShayariScreen(cateName:String,allShayariList: List<AllShayari>) {
   val context = LocalContext.current
   val clipboardManager = LocalClipboardManager.current
   val selectedStates = remember { mutableStateListOf<Boolean>().apply { addAll(List(allShayariList.size) { false }) } }

    Scaffold(
       modifier = Modifier.fillMaxSize(),
       topBar = {
          Row(
             horizontalArrangement = Arrangement.SpaceBetween,
             verticalAlignment = Alignment.CenterVertically,
             modifier = Modifier.padding(10.dp)
          ) {
              Text(modifier = Modifier.fillMaxWidth(),
                  text = cateName,
                  style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(800), fontFamily = FontFamily(
                      Font(R.font.mochiypop)), color = Color(LightColorScheme.primary.toArgb()), textAlign = TextAlign.Center))
          }
       }) {
       LazyColumn(modifier = Modifier.padding(it)) {
           itemsIndexed(allShayariList) { index, shayariItems ->
               Card(modifier = Modifier
                   .fillMaxWidth()
                   .padding(10.dp))
               {
                   Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                       .fillMaxWidth()
                       .fillMaxHeight()
                       .background(
                           brush = Brush.linearGradient(
                               listOf(
                                   Color(0xFF990707),
                                   Color(0XFFECE8D8)
                               )
                           )
                       )
                   ) {
                       Text(text = shayariItems.shayari.toString(), textAlign = TextAlign.Center, modifier = Modifier.padding(10.dp), style = TextStyle(fontSize = 16.sp, fontFamily = FontFamily(
                           Font(R.font.mochiypop)), color = Color.White, fontWeight = FontWeight(600)
                       ))
                       Spacer(modifier = Modifier.height(10.dp))
                       Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly) {
                           Icon(imageVector = Icons.Outlined.ContentCopy, contentDescription = null, tint = Color.White,
                               modifier = Modifier.clickable {
                                   // Clipboard saves this clip object
                                   clipboardManager.setText(AnnotatedString(shayariItems.shayari.toString()))
                                   Toast.makeText(context, "Copied to Clipboard", Toast.LENGTH_SHORT).show()
                               })
                           Icon(imageVector = if (selectedStates[index]) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                               contentDescription = null, tint = if(selectedStates[index]) Color.Red else Color.White,
                               modifier = Modifier.clickable {
                                   selectedStates[index] = !selectedStates[index]
                                   val message = if (selectedStates[index]) {
                                       "Item added to your Favourite List."
                                   } else {
                                       "Item removed from your Favourite List."
                                   }
                                   Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                               })
                           Icon(imageVector = Icons.Outlined.Share, contentDescription = null, tint = Color.White,
                               modifier = Modifier.clickable {
                                 val intent = Intent()
                                 intent.setAction(Intent.ACTION_SEND)
                                 intent.type = "text/plain"
                                 intent.putExtra(Intent.EXTRA_TEXT,shayariItems.shayari.toString())
                                 context.startActivity(intent)
                               })
                           Icon(imageVector = Icons.Outlined.Whatsapp, contentDescription = null, tint = Color.White,
                               modifier = Modifier.clickable {
                                  val intent = Intent(Intent.ACTION_SEND)
                                   intent.type = "text/plain"
                                   intent.setPackage("com.whatsapp")
                                   intent.putExtra(Intent.EXTRA_TEXT, shayariItems.shayari.toString())
                                   if (intent.resolveActivity(context.packageManager) == null) {
                                       Toast.makeText(context, "Please install WhatsApp App to share content.", Toast.LENGTH_SHORT).show()
                                   }else {
                                   context.startActivity(intent)}
                               })
                       }
                       Spacer(modifier = Modifier.height(10.dp))
                   }
               }
           }
       }
   }
}



