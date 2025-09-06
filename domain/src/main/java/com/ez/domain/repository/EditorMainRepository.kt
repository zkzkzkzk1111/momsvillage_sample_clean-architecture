package com.ez.domain.repository

import com.ez.domain.model.EditorMain
import com.ez.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface EditorMainRepository {
    fun getEditorMainBoard(): Flow<ApiResult<List<EditorMain>>>
}