package com.example.homework2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource

@Composable
fun AddPostPage(user: User) {
    val drawableImages = listOf(
        R.drawable.earth,
        R.drawable.mars,
        R.drawable.profile_pic1,
        R.drawable.profile_pic2,
        R.drawable.profile_pic3,
        R.drawable.profile_pic,
        R.drawable.sun,
        R.drawable.moon,
        R.drawable.unknown_user
    )

    var imageResource by remember { mutableIntStateOf(R.drawable.earth) }
    var caption by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Select an Image:", style = MaterialTheme.typography.headlineMedium)

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(drawableImages) { drawableRes ->
                Image(
                    painter = painterResource(id = drawableRes),
                    contentDescription = "Selectable Image",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .clickable {
                            imageResource = drawableRes
                        }
                        .background(if (imageResource == drawableRes) Color.LightGray else Color.Transparent),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Selected Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Gray)
                .padding(8.dp),
            contentScale = ContentScale.Crop
        )

        TextField(
            value = caption,
            onValueChange = { caption = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Caption") }
        )

        Button(
            onClick = {
                val newPost = Post(
                    img = imageResource,
                    user = user,
                    caption = caption,
                    likes = 0
                )
                user.addPost(newPost)
                caption = ""
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Upload Post")
        }
    }
}