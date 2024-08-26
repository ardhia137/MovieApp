package com.example.movieapp.di

import com.example.movieapp.MainViewModel
import com.example.movieapp.core.domain.usecase.MovieInteractor
import com.example.movieapp.core.domain.usecase.MovieUseCase
import com.example.movieapp.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}