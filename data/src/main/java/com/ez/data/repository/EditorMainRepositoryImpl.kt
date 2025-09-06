package com.ez.data.repository

import com.ez.data.bound.flowDataResource
import com.ez.data.remote.EditorMainRemoteDataSource
import com.ez.data.toDomain
import com.ez.domain.model.EditorMain
import com.ez.domain.repository.EditorMainRepository
import com.ez.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class EditorMainRepositoryImpl @Inject constructor(
    private val editorRemoteDataSource: EditorMainRemoteDataSource
) : EditorMainRepository {
    override fun getEditorMainBoard(): Flow<ApiResult<List<EditorMain>>> =
        flowDataResource { editorRemoteDataSource.getEditorMainBoard().toDomain() }
}

