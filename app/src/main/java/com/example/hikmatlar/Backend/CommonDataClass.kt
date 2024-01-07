package com.example.hikmatlar.Backend

import com.example.hikmatlar.Backend.Api.ApiService.Hashtag

interface CommonDataClass {
    val idT: Long
    val author: String
    val created_at: String
    val hashtags: List<Hashtag>
    val id: Int
    val text: String
    val translation: String
}