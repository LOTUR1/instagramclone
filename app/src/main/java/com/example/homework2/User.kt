package com.example.homework2

class User(
    val name: String,
    val bio: String = "",
    val profilePic: Int = R.drawable.unknown_user
){
    val userPosts: MutableList<Post> = mutableListOf()

    fun addPost(post: Post){
        userPosts.add(post)
    }
}