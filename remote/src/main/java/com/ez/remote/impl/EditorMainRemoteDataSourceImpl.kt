package com.ez.remote.impl

import com.ez.data.model.EditorMainEntity
import com.ez.data.remote.EditorMainRemoteDataSource
import com.ez.remote.api.ApiService
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class EditorMainRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : EditorMainRemoteDataSource {

    override suspend fun getEditorMainBoard(): List<EditorMainEntity> = coroutineScope {
        val editorMainList = apiService.getEditorMainBoard()

        editorMainList.data.map { board ->
            EditorMainEntity(
                postId = board.postId,
                userId = board.userId,
                postTitle = board.postTitle,
                postContent = board.postContent,
                postCreateTime = board.postCreateTime,
                postUpdateTime = board.postUpdateTime,
                categoryId = board.categoryId,
                categoryName = board.categoryName,
                postImageurl = board.postImageurl,
                userNickname = board.userNickname,
                userProfileImage = board.userProfileImage,
                userStatusMessage = board.userStatusMessage,
                editorRankId = board.editorRankId,
                editorRank = board.editorRank,
                editorRankImage = board.editorRankImage,
            )
        }
    }
}




