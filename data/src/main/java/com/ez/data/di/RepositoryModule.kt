package com.ez.data.di

import com.ez.data.repository.EditorMainRepositoryImpl
import com.ez.domain.repository.EditorMainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(repo: EditorMainRepositoryImpl ): EditorMainRepository

}