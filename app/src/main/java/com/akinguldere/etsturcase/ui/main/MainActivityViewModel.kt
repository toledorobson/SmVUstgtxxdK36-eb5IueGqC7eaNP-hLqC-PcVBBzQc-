package com.akinguldere.etsturcase.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akinguldere.etsturcase.model.TMDBResponse
import com.akinguldere.etsturcase.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: MoviesRepository) :
    ViewModel() {
    private val _response = MutableLiveData<TMDBResponse>()
    private val _isLoading = MutableLiveData<Boolean>()

    val responseMovies: LiveData<TMDBResponse> get() = _response
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getMovies(page: Int = 1) = viewModelScope.launch {
        _isLoading.postValue(true)

        repository.getPopularMovies(page).let { response ->

            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                println("Error ${response.errorBody()}")
            }

            // api call completed.
            _isLoading.postValue(false)

        }
    }
}