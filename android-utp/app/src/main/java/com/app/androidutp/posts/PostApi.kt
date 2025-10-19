package com.app.androidutp.posts

import retrofit2.http.GET

interface PostApi {
	@GET("/posts")
	suspend fun getPosts(): List<Post>
}