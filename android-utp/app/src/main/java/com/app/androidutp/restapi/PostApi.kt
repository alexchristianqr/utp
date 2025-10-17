package com.app.androidutp.restapi

import retrofit2.http.GET

interface PostApi {
	@GET("/posts")
	suspend fun getPosts(): List<Post>
}