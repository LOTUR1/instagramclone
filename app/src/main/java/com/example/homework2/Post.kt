package com.example.homework2

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController

class Post(
    val img: Int,
    val user: User,
    val caption: String,
    var likes: Int = 0,
    var comments: MutableList<Comment> = mutableListOf(),
) {
    @Composable
    fun DisplayImg(){
        Image(
            painter = painterResource(img),
            contentDescription = "desc",
            modifier = Modifier
                .aspectRatio(1f)
                .clip(RectangleShape)
                .padding(1.dp)
            ,
            contentScale = ContentScale.Crop
        )
    }

    @Composable
    fun DisplayPost(navController: NavController){
        var isLiked by rememberSaveable { mutableStateOf(false) }
        val imageResource = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder
        var showCommentsDialog by remember { mutableStateOf(false) }

        Column{
            Image(
                painter = painterResource(img),
                contentDescription = "desc",
                modifier = Modifier
                    .heightIn(max = 1080.dp)
                    .aspectRatio(1f)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = imageResource,
                        contentDescription = "Like",
                        modifier = Modifier
                            .size(26.dp)
                            .clickable{
                                if (isLiked) likes-- else likes++
                                isLiked = !isLiked },
                    )

                    Text(
                        text = "$likes",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(10.dp, 0.dp)
                ){
                    Icon(
                        imageVector = Icons.Default.MailOutline,
                        contentDescription = "Comment",
                        modifier = Modifier
                            .size(26.dp)
                            .clickable{ showCommentsDialog = true }
                    )

                    Text(
                        text = "${comments.size}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(1.dp, 0.dp)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(5.dp, 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${user.name} ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { navController.navigate("profile_page/${user.name}") }
                )

                Text(
                    text = caption,
                    fontSize = 14.sp,
                )
            }
        }

        if (showCommentsDialog) {
            Dialog(onDismissRequest = { showCommentsDialog = false }) {
                CommentsPageDialogContent(post = this@Post, onDismiss = { showCommentsDialog = false })
            }
        }
    }
}