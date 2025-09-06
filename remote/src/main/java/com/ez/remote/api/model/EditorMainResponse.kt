package com.ez.remote.api.model

import com.ez.data.model.EditorMainEntity
import com.ez.remote.RemoteMapper
import com.google.gson.annotations.SerializedName

data class EditorMainResponse (
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data : List<data>,
)

data class data(
    @SerializedName("postId")
    val postId: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("postTitle")
    val postTitle: String,
    @SerializedName("postContent")
    val postContent: String,
    @SerializedName("postCreateTime")
    val postCreateTime: String,
    @SerializedName("postUpdateTime")
    val postUpdateTime: String,
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("categoryName")
    val categoryName: String,
    @SerializedName("postImageurl")
    val postImageurl: String,
    @SerializedName("userNickname")
    val userNickname: String,
    @SerializedName("userProfileImage")
    val userProfileImage: String,
    @SerializedName("userStatusMessage")
    val userStatusMessage: String,
    @SerializedName("editorRankId")
    val editorRankId: Int,
    @SerializedName("editorRank")
    val editorRank: String,
    @SerializedName("editorRankImage")
    val editorRankImage: String,
):RemoteMapper<EditorMainEntity> {
    override fun toData(): EditorMainEntity =
        EditorMainEntity(
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