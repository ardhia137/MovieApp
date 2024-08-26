package com.example.movieapp.core.data.source.remote.network

import com.example.movieapp.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc")
    suspend fun  getList(): ListMovieResponse
}