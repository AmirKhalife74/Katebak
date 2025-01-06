package com.example.katebak.data.di

import android.content.Context
import com.example.katebak.data.remote.Api
import com.example.katebak.data.repositories.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    @Singleton
    fun provideMainRepository(api: Api,@ApplicationContext context: Context):MainRepository{
        return MainRepository(api,context)
    }
}