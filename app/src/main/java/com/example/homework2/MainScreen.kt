package com.example.homework2

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.compose.foundation.layout.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val homePage = HomeFeedPage()

    val user1 = User("Sponge Bob", "Sponge", R.drawable.profile_pic1)
    val user2 = User("Patrick", "Star", R.drawable.profile_pic2)
    val user3 = User("Squidward", "Octopus", R.drawable.profile_pic3)
    val user4 = User("NoName", "NoName", R.drawable.unknown_user)

    val users = listOf(user1, user2, user3, user4)

    for (i in 1..3){
        user1.addPost(Post(R.drawable.sun, user1, "Star", 0))
        user2.addPost(Post(R.drawable.moon, user2, "Satellite", 1))
        user3.addPost(Post(R.drawable.earth, user3, "Planet", 2))
        user4.addPost(Post(R.drawable.mars, user4, "Another Planet", 3))
    }

    for (i in users){
        for (j in i.userPosts){
            homePage.posts.add(j)
        }
    }

    homePage.posts.shuffle()

    generateNotifications()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { homePage.DisplayPosts(navController)}
            composable("search") { SearchPage(users, navController) }
            composable("profile") { ProfilePage(user).DisplayProfile(navController) }
            composable("add_post") { AddPostPage(user) }
            composable("notifications") { NotificationsPage() }
            composable("profile_page/{userName}") { backStackEntry ->
                val userName = backStackEntry.arguments?.getString("userName")
                val user12 = users.find { it.name == userName }
                if (user12 != null) {
                    ProfilePage(user12).DisplayProfile(navController)
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavigationItem("Home", "home", Icons.Default.Home),
        NavigationItem("Search", "search", Icons.Default.Search),
        NavigationItem("Add Post", "add_post", Icons.Default.Add),
        NavigationItem("Notifications", "notifications", Icons.Default.Notifications),
        NavigationItem("Profile", "profile", Icons.Default.Person)
    )

    NavigationBar {
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                selected = currentDestination?.route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

data class NavigationItem(val label: String, val route: String, val icon: ImageVector)