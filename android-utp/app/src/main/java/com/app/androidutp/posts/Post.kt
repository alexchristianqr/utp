package com.app.androidutp.posts

import kotlinx.serialization.Serializable

@Serializable
data class Post(
	var userId: Int = 0,
	var id: Int = 0,
	var title: String = "",
	var body: String = "",
)