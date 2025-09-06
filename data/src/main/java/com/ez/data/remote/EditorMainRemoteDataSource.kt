package com.ez.data.remote

import com.ez.data.model.EditorMainEntity

interface EditorMainRemoteDataSource {
    suspend fun getEditorMainBoard():List<EditorMainEntity>
}