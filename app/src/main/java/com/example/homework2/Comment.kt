package com.example.homework2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class Comment(
    val post: Post,
    val user: User,
    val comment: String
){
    @Composable
    fun ShowComment(){
        Row(
            modifier = Modifier.padding(0.dp, 8.dp)
        ) {
            Image(
                painter = painterResource(user.profilePic),
                contentDescription = "User_profile",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(5.dp, 0.dp)
            ){
                Text(
                    text = user.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = comment,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun CommentsPageDialogContent(post: Post, onDismiss: () -> Unit) {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp)
        ) {
            Button(
                onClick = { onDismiss() },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
            ) {
                items(post.comments) { comm -> comm.ShowComment() }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(user.profilePic),
                contentDescription = "User photo",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .align(Alignment.Bottom),
                contentScale = ContentScale.Crop
            )

            BasicTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
                    .defaultMinSize(minHeight = 56.dp)
                    .fillMaxWidth()
                    .padding(16.dp),
                textStyle = TextStyle(color = Color.Black),
                decorationBox = { innerTextField ->
                    if (text.isEmpty()) {
                        Text(
                            text = "Add comment",
                            color = Color.Gray
                        )
                    }
                    innerTextField()
                }
            )

            if (text.isNotEmpty()) {
                Button(
                    onClick = {
                        post.comments.add(Comment(post, user, text))
                        text = ""
                    },
                    modifier = Modifier.size(45.dp).align(Alignment.Bottom),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Upload"
                    )
                }
            }
        }
    }
}