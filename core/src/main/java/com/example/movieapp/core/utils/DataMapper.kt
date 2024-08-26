package com.example.movieapp.core.utils

import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.data.source.local.entity.MovieEntity
import com.example.movieapp.core.data.source.remote.response.MovieResponse

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                posterPath = it.posterPath,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                posterPath = it.posterPath,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        releaseDate = input.releaseDate,
        voteAverage = input.voteAverage,
        posterPath = input.posterPath,
        isFavorite = input.isFavorite
    )
}