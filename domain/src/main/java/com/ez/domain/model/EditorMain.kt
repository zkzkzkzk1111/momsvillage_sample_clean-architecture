package com.ez.domain.model

data class EditorMain(
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
)