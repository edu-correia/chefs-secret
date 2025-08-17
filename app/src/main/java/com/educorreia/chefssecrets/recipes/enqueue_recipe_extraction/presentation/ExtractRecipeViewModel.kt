package com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educorreia.chefssecrets.R
import com.educorreia.chefssecrets.core.data.domain.interfaces.RecipesRepository
import com.educorreia.chefssecrets.core.ui.auth.Authenticator
import com.educorreia.chefssecrets.core.ui.auth.UserState
import com.educorreia.chefssecrets.core.ui.utils.UiText.StringResource
import com.educorreia.chefssecrets.recipes.common.domain.models.VideoPreviewUIModel
import com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.domain.exceptions.ExtractRecipeValidationException
import com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.domain.exceptions.ExtractRecipeValidationException.*
import com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.domain.use_cases.ValidateRecipeVideoUrl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExtractRecipeViewModel(
    private val videoUrl: String,
    private val recipesRepository: RecipesRepository,
    private val authenticator: Authenticator,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ExtractRecipeUiState())
    val uiState = _uiState.asStateFlow()

    val userState: StateFlow<UserState> = authenticator.userState

    private val _effect = MutableSharedFlow<ExtractRecipeEffect>()
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

    fun onEvent(event: ExtractRecipeAction) {
        when (event) {
            is ExtractRecipeAction.Submit -> submitRecipeExtraction()
            is ExtractRecipeAction.LoginWithGoogle -> {
                viewModelScope.launch {
                    authenticator.loginWithGoogle()
                }
            }
        }
    }

    fun submitRecipeExtraction() {
        viewModelScope.launch {
            try {
                ValidateRecipeVideoUrl().execute(videoUrl)

                recipesRepository.enqueueExtraction(videoUrl)

                _uiState.value = _uiState.value.copy(showSuccessMessage = true)
            } catch (e: ExtractRecipeValidationException) {
                Log.e("ViewModel", "Validation error for videoUrl: $videoUrl", e)
                val errorMessage = when (e) {
                    is EmptyVideoUrlException -> StringResource(R.string.error_empty_video_url)
                    is InvalidLinkForVideoUrlException -> StringResource(R.string.error_invalid_video_url)
                    is VideoUrlMustBeFromInstagramException -> StringResource(R.string.error_video_url_must_be_from_instagram)
                    else -> StringResource(R.string.unknown_error)
                }
                _effect.emit(ExtractRecipeEffect.ShowErrorMessage(errorMessage))
            } catch (e: Exception) {
                Log.e("ViewModel", "Error enqueuing extraction for URL: $videoUrl", e)
                _effect.emit(ExtractRecipeEffect.ShowErrorMessage(
                    StringResource(R.string.unknown_error))
                )
            }
        }
    }
}