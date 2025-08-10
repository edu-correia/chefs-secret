package com.educorreia.chefssecrets.core.ui.share

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.educorreia.chefssecrets.core.data.domain.interfaces.UserAuthService
import com.educorreia.chefssecrets.core.ui.auth.AuthAction
import com.educorreia.chefssecrets.core.ui.auth.Authenticator
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.core.ui.utils.ObserveAsEvents
import com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation.EnqueueRecipeExtractionScreenRoot
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

class ShareActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shareData: String? = when (intent?.action) {
            Intent.ACTION_SEND -> {
                intent.getStringExtra(Intent.EXTRA_TEXT)
            }
            else -> null
        }

        setContent {
            AppTheme {
                val scope = rememberCoroutineScope()

                val authenticator = koinInject<Authenticator>()
                val userAuthService = koinInject<UserAuthService>()

                val context = LocalContext.current
                ObserveAsEvents(flow = authenticator.authActions) { action ->
                    when (action) {
                        is AuthAction.LoginWithGoogle -> {
                            scope.launch {
                                userAuthService.loginWithGoogle(context) {
                                    authenticator.refreshAuthenticatedUser()
                                }
                            }
                        }
                        is AuthAction.Logout -> {
                            scope.launch {
                                userAuthService.logout(context) {
                                    authenticator.refreshAuthenticatedUser()
                                }
                            }
                        }
                    }
                }

                if (shareData != null) {
                    EnqueueRecipeExtractionScreenRoot(
                        videoUrl = shareData,
                        onDismiss = { finish() }
                    )
                } else {
                    finish()
                }
            }
        }
    }
}