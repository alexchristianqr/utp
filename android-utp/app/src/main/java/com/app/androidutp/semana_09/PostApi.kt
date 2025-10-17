package com.app.androidutp.semana_09

import retrofit2.http.GET

interface PostApi {
	@GET("/posts")
	suspend fun getPosts(): List<Post>
}