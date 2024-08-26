package com.example.movieapp.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailViewModel: DetailViewModel by viewModel()


    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val detail = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetail(detail)
    }

    private fun showDetail(detail: Movie?) {
        detail?.let {
            binding.tvMovieOverview.text = detail.overview
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2/${detail.posterPath}")
                .into(binding.imgMoviePoster)
            binding.tvMovieName.text = detail.originalTitle
            binding.tvMovieRating.text = "Rating : %.1f".format(detail.voteAverage)
            binding.tvMovieReleaseDate.text = "Tanggal Rilis : ${detail.releaseDate}"
            var statusFavorite = detail.isFavorite
            setStatusFavorite(statusFavorite)
            binding.btnLike.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(detail, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnLike.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.btnLike.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}