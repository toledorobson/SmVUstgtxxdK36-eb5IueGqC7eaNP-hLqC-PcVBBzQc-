package com.akinguldere.etsturcase.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.akinguldere.etsturcase.EtsActivity
import com.akinguldere.etsturcase.R
import com.akinguldere.etsturcase.databinding.ActivityMainBinding
import com.akinguldere.etsturcase.model.MovieItem
import com.akinguldere.etsturcase.ui.details.DetailsActivity
import com.akinguldere.etsturcase.utils.extensions.gone
import com.akinguldere.etsturcase.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MainActivity : EtsActivity() {

    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)

    private val viewModel: MainActivityViewModel by viewModels()

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    private var moviesList = arrayListOf<MovieItem>()
    private var page = 1 // For pagination

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moviesAdapter.setOnItemClickListener {
            // Go to movie details
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("movie", it)
            startActivity(intent)
        }

        binding.apply {
            lifecycleOwner = this@MainActivity
            adapter = this@MainActivity.moviesAdapter

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (!recyclerView.canScrollVertically(1) && dy > 0) {
                        // scrolled to bottom
                        // can get new movies
                        viewModel.getMovies(page)
                    }
                }
            })
        }

        viewModel.getMovies(page)
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.responseMovies.observe(this) { response ->
            if (response != null) {
                // get position for scroll
                val position = moviesList.size

                // increase page for next call
                page += 1

                moviesList.addAll(response.movies)
                moviesAdapter.differ.submitList(moviesList.toList())

                // for better user experience
                binding.recyclerView.smoothScrollToPosition(position)
            }
        }

        viewModel.isLoading.observe(this) { response ->
            // Show progressbar when waiting for api result
            if (response) {
                binding.progressBar.visible()
            } else {
                binding.progressBar.gone()
            }
        }
    }

    // calling on create option menu
    // layout to inflate our menu file.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // below line is to get our inflater
        val inflater = menuInflater

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu)

        // below line is to get our menu item.
        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)

        // getting search view of our item.
        val searchView: SearchView = searchItem.actionView as SearchView

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(msg)
                return false
            }
        })
        return true
    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredList: ArrayList<MovieItem> = ArrayList()

        // running a for loop to compare elements.
        for (item in moviesList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.name.lowercase(Locale.getDefault())
                    .contains(text.lowercase(Locale.getDefault()))
            ) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredList.add(item)
            }
        }
        if (filteredList.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no movie found.
            Toast.makeText(this, "No movie found!", Toast.LENGTH_SHORT).show()
        }
        moviesAdapter.differ.submitList(filteredList)

    }
}