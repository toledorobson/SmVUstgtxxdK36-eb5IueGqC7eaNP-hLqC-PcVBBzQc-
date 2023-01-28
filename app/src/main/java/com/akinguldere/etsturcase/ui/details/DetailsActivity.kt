package com.akinguldere.etsturcase.ui.details

import android.os.Bundle
import com.akinguldere.etsturcase.EtsActivity
import com.akinguldere.etsturcase.R
import com.akinguldere.etsturcase.databinding.ActivityDetailsBinding
import com.akinguldere.etsturcase.model.MovieItem

class DetailsActivity : EtsActivity() {

    private val binding by binding<ActivityDetailsBinding>(R.layout.activity_details)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieItem = intent.getSerializableExtra("movie") as MovieItem

        binding.apply {
            lifecycleOwner = this@DetailsActivity
            movie = movieItem
        }
    }
}