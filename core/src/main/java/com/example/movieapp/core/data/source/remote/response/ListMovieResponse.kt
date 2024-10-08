package com.example.movieapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("results")
	val results: List<MovieResponse?>
)


