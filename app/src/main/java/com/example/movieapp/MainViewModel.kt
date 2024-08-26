package com.example.movieapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.movieapp.core.domain.usecase.MovieUseCase

class MainViewModel (movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}