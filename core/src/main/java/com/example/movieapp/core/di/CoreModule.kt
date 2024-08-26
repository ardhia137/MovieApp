package com.example.movieapp.core.di

import androidx.room.Room
import com.example.movieapp.core.data.source.local.room.MovieDatabase
import com.example.movieapp.core.data.source.remote.RemoteDataSource
import com.example.movieapp.core.data.source.remote.network.ApiService
import com.example.movieapp.core.domain.repository.IMovieRepository
import com.example.movieapp.core.utils.AppExecutors
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "Movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}
val authInterceptor = Interceptor { chain ->
    val req = chain.request()
    val requestHeaders = req.newBuilder()
        .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ODRjNTkxNzdmODYyNDhhNDBmYjJiMTJmMDA0YTgyYyIsIm5iZiI6MTcyNDEyNzA3Ny42MDQxNjIsInN1YiI6IjY2YzQxNjg3M2M0MDQ3MjQwNWM5YmE3ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.f45xDXsFEKAr84BJjjhNs6qljxyIDy3uM0K0d6-Royg")
        .build()
    chain.proceed(requestHeaders)
}
val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(authInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/discover/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { com.example.movieapp.core.data.source.local.LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> { com.example.movieapp.core.data.MovieRepository(get(), get(), get()) }
}
