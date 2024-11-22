package com.example.homework2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

class ProfilePage(
    val user: User
) {
    @Composable
    fun DisplayProfile(navController: NavController){
        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp)
        ){
            Column {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Column {
                        Image(
                            painter = painterResource(user.profilePic),
                            contentDescription = "profilePic",
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = user.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = user.bio,
                            fontSize = 14.sp
                        )
                    }

                    Text(
                        text = "${user.userPosts.size} posts",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(10.dp, 0.dp)
                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(3)
            ) {
                items(user.userPosts.reversed()) { post -> post.DisplayImg()}
            }
        }
    }
}

val user = User("Name", "BIO BIO BIO", R.drawable.profile_pic)