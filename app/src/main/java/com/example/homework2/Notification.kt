package com.example.homework2
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.ui.text.font.FontWeight

data class Notification(
    val type: String,
    val message: String,
)

@Composable
fun NotificationsPage() {
    var notificationsList by remember { mutableStateOf(generateNotifications().toMutableList()) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(notificationsList) { notification ->
            NotificationItem(
                notification = notification,
                onDelete = { notificationsList =
                    (notificationsList - notification).toMutableList()
                }
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun NotificationItem(notification: Notification, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = notification.type,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = notification.message,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Clear",
                modifier = Modifier
                    .padding(20.dp, 0.dp)
                    .clickable {
                        onDelete()
                    }
            )
        }
    }
}

fun generateNotifications(): List<Notification> {
    return listOf(
        Notification(type = "Like", message = "John liked your post."),
        Notification(type = "Comment", message = "Alice commented: 'Great picture!'"),
        Notification(type = "Follow", message = "Bob started following you."),
        Notification(type = "Like", message = "Emma liked your photo."),
        Notification(type = "Comment", message = "Michael commented: 'Amazing!'"),
        Notification(type = "Follow", message = "Sophia started following you."),
        Notification(type = "Like", message = "Lucas liked your story."),
        Notification(type = "Comment", message = "Olivia commented: 'Looks great!'"),
        Notification(type = "Like", message = "Liam liked your post."),
        Notification(type = "Follow", message = "James started following you.")
    )
}