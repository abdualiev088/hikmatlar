package com.example.hikmatlar.Backend.Api.ApiService

data class Quote(
    val author: String,
    val created_at: String,
    val hashtags: List<Hashtag>,
    val id: Int,
    val text: String,
    val translation: String
)