package com.example.homework2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


class HomeFeedPage {
    val posts: MutableList<Post> = mutableListOf()

    @Composable
    fun DisplayPosts(navController: NavController){
        LazyColumn{
            items(posts){ post ->
                Column(modifier = Modifier.padding(bottom = 16.dp))
                {
                    post.DisplayPost(navController)
                }
            }
        }
    }
}
