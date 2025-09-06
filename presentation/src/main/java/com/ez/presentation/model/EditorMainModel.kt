package com.ez.presentation.model

import com.ez.domain.model.EditorMain

data class EditorMainModel(
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

fun EditorMain.toPresentation(): EditorMainModel = EditorMainModel(
    postId,
    userId,
    postTitle,
    postContent,
    postCreateTime,
    postUpdateTime,
    categoryId,
    categoryName,
    postImageurl,
    userNickname,
    userProfileImage,
    userStatusMessage,
    editorRankId,
    editorRank,
    editorRankImage
)