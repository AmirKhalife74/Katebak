package com.example.katebak.data.di

import com.example.katebak.data.repositories.MainRepository
import com.example.katebak.viewModels.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelsModule {
    @Provides
    @Singleton
    fun provideMainViewModel(
        mainRepository: MainRepository
    ): MainViewModel
    {
        return MainViewModel(mainRepository)
    }
}