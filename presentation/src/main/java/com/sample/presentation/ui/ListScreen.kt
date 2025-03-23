package com.sample.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sample.presentation.viewmodel.UsersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(model: UsersViewModel = hiltViewModel()) {
    val newslist by model.newsList.collectAsState()
    val errorMsg by model.errorData.collectAsState()
    LaunchedEffect(newslist) {
        model.fetchData("business", "538c416021af406d9d75dc4c04c93267")

    }
   Scaffold(topBar = {
       TopAppBar(title = { Text(text = "Test") },
           colors = TopAppBarDefaults.topAppBarColors(Color.Blue)
       )
   }) {paddingValues ->

       Box(modifier = Modifier.padding(paddingValues)) {
           Column(modifier = Modifier.padding(6.dp).fillMaxSize()) {
               Card(shape = RectangleShape,
                   modifier = Modifier.fillMaxWidth(),

                   colors = CardDefaults.cardColors(Color.Gray),
                   elevation = CardDefaults.elevatedCardElevation(2.dp)
                   ) {

                   LazyColumn {
                       items(newslist){items->
                           Text( text =items.country, modifier = Modifier.padding(5.dp) )
                       }
                   }

               }
           }
       }

   }
















   /* Scaffold(topBar = {TopAppBar(title = { Text(text = "Test") },
        colors = TopAppBarDefaults.topAppBarColors(Color.Red)
    )
    }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

                Column(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(modifier = Modifier.wrapContentSize()) {
                        items(newslist) { items ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                            ) {

                                Text(
                                    text = items.country,
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .padding(8.dp)
                                )
                            }

                        }
                    }

                }
        }

    }*/
}