package com.akinguldere.etsturcase.di

import com.akinguldere.etsturcase.ui.main.MoviesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {
    @Singleton
    @Provides
    fun provideCategoriesAdapter(): MoviesAdapter {
        return MoviesAdapter()
    }

}