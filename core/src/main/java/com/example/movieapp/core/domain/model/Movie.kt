package com.example.movieapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val releaseDate: String,
    val voteAverage: Double,
    val posterPath: String,
    val isFavorite: Boolean
) : Parcelable