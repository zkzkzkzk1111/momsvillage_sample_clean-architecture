package com.ez.remote

interface RemoteMapper<DataModel> {
    fun toData(): DataModel
}
