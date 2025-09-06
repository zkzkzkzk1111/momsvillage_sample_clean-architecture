package com.ez.data.model

import com.ez.data.DataMapper
import com.ez.domain.model.EditorMain

data class EditorMainEntity (
    val postId: Int,
    val userId: Int,
    val postTitle: String,
    val postContent: String,
    val postCreateTime: String,
    val postUpdateTime: String,
    val categoryId: Int,
    val categoryName: String,
    val postImageurl: String,
    val userNickname: String,
    val userProfileImage: String,
    val userStatusMessage: String,
    val editorRankId: Int,
    val editorRank: String,
    val editorRankImage: String,
) : DataMapper<EditorMain>{
    override fun toDomain(): EditorMain =
        EditorMain(
            postId = postId,
            userId = userId,
            postTitle = postTitle,
            postContent = postContent,
            postCreateTime = postCreateTime,
            postUpdateTime = postUpdateTime,
            categoryId = categoryId,
            categoryName = categoryName,
            postImageurl = postImageurl,
            userNickname = userNickname,
            userProfileImage = userProfileImage,
            userStatusMessage = userStatusMessage,
            editorRankId = editorRankId,
            editorRank = editorRank,
            editorRankImage = editorRankImage,
        )
}