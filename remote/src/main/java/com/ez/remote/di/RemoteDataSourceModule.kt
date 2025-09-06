package com.ez.remote.di

import com.ez.data.remote.EditorMainRemoteDataSource
import com.ez.remote.impl.EditorMainRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDataSourceModule{
    @Binds
    @Singleton
    abstract fun bindEditorMainRemoteDataSource(source: EditorMainRemoteDataSourceImpl) : EditorMainRemoteDataSource

}