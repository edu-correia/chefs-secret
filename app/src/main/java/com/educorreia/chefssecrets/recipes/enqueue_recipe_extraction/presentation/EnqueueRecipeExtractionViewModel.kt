package com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educorreia.chefssecrets.core.data.domain.interfaces.RecipesRepository
import com.educorreia.chefssecrets.core.ui.auth.Authenticator
import com.educorreia.chefssecrets.core.ui.auth.UserState
import com.educorreia.chefssecrets.recipes.common.domain.models.VideoPreviewUIModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EnqueueRecipeExtractionViewModel(
    private val videoUrl: String,
    private val recipesRepository: RecipesRepository,
    private val authenticator: Authenticator,
) : ViewModel() {
    private val _uiState = MutableStateFlow(EnqueueRecipeExtractionUiState())
    val uiState = _uiState.asStateFlow()

    val userState: StateFlow<UserState> = authenticator.userState

    private val _effect = MutableSharedFlow<EnqueueRecipeExtractionEffect>()
    val effect = _effect.asSharedFlow()

    init {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            val videoPreview = recipesRepository.getVideoPreview(videoUrl)

            // Simulate a little latency to better examine the loading animation:
            // delay(2000)

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                videoPreview = VideoPreviewUIModel.fromDomain(videoPreview)
            )
        }
    }

    fun onEvent(event: EnqueueRecipeExtractionAction) {
        when (event) {
            is EnqueueRecipeExtractionAction.Submit -> submit()
            is EnqueueRecipeExtractionAction.LoginWithGoogle -> {
                viewModelScope.launch {
                    authenticator.loginWithGoogle()
                }
            }
        }
    }

    private fun submit() {

    }
}