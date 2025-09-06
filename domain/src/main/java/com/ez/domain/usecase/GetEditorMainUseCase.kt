package com.ez.domain.usecase

import com.ez.domain.repository.EditorMainRepository
import javax.inject.Inject

class GetEditorMainUseCase @Inject constructor(private val editorMainRepository: EditorMainRepository) {
    operator fun invoke() =
        editorMainRepository.getEditorMainBoard()
}