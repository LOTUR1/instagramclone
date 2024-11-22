package com.example.homework2

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun SearchPage(users: List<User>, navController: NavController) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    val filteredUsers = users.filter {
        it.name.startsWith(searchQuery.text, ignoreCase = true)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            label = { Text("Search") }
        )

        if (filteredUsers.isEmpty()) {
            Text(
                text = "No results found",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(filteredUsers) { user ->
                    UserProfileItem(user, navController)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }
}

@Composable
fun UserProfileItem(user: User, navController: NavController) {
    Row(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = user.profilePic),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(48.dp)
                .fillMaxSize()
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(6.dp)) {
            Text(text = user.name, fontWeight = FontWeight.Bold, modifier = Modifier
                .clickable {navController.navigate("profile_page/${user.name}")})
            Text(text = user.bio, style = MaterialTheme.typography.bodyMedium)
        }
    }
}