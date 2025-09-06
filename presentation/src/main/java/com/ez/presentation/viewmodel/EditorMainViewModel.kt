package com.ez.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ez.domain.usecase.GetEditorMainUseCase
import com.ez.domain.util.onFailure
import com.ez.domain.util.onSuccess
import com.ez.presentation.model.EditorMainModel
import com.ez.presentation.model.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorMainViewModel @Inject constructor(
    private val getEditorMainUseCase: GetEditorMainUseCase,
): ViewModel() {

    private val _boards = MutableStateFlow<List<EditorMainModel>>(emptyList())
    val boards = _boards.asStateFlow()

    private val _error = MutableStateFlow<Throwable?>(null)
    val error = _error.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    fun fetchEditorMainBoards() {
        viewModelScope.launch {
            _loading.value = true
            getEditorMainUseCase().collect { result ->
                result.onSuccess { entities ->
                    _boards.value = entities.map { it.toPresentation() }
                }.onFailure { throwable ->
                    _error.value = throwable
                }
                _loading.value = false
            }
        }
    }
}