package com.example.movieapp.detail

import androidx.lifecycle.ViewModel
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus:Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}