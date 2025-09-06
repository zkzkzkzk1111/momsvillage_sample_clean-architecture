package com.ez.presentation.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ez.presentation.viewmodel.EditorMainViewModel

@Composable
fun EditorMain(
    EditorMainViewModel: EditorMainViewModel = hiltViewModel()
){
    val boards by EditorMainViewModel.boards.collectAsState()

    LaunchedEffect(Unit) {
        EditorMainViewModel.fetchEditorMainBoards()

    }
    Log.d("boadrsinfo",boards.toString())

}