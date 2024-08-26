package com.example.movieapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.core.ui.MovieAdapter
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {



    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu1 -> {
                    val uri = Uri.parse("movieapp://favorite")
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                    true
                }
                else -> false
            }
        }


        mainViewModel.movie.observe(this, { movie ->
            if (movie != null) {
                when (movie) {
                    is com.example.movieapp.core.data.Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is com.example.movieapp.core.data.Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        movieAdapter.setData(movie.data)
                    }
                    is com.example.movieapp.core.data.Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.tvError.visibility = View.VISIBLE
                        // Set error message to view
                        binding.viewError.tvError.text = movie.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })

        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
