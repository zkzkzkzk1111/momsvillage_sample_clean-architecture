package com.ez.remote.api

import com.ez.remote.api.model.EditorMainResponse
import retrofit2.http.GET

interface ApiService {
    @GET("editorRouter/village/editorMain")
    suspend fun getEditorMainBoard():EditorMainResponse
}