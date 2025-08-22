package com.educorreia.chefssecrets.core.ui.share

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.educorreia.chefssecrets.core.data.domain.interfaces.UserAuthService
import com.educorreia.chefssecrets.core.ui.auth.AuthAction
import com.educorreia.chefssecrets.core.ui.auth.Authenticator
import com.educorreia.chefssecrets.core.ui.scaffold.IGNORE_WINDOW_INSETS
import com.educorreia.chefssecrets.core.ui.snackbar.CustomSnackbar
import com.educorreia.chefssecrets.core.ui.snackbar.CustomSnackbarVisuals
import com.educorreia.chefssecrets.core.ui.snackbar.LocalSnackbarHostState
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.core.ui.utils.ObserveAsEvents
import com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation.ExtractRecipeScreenRoot
import com.google.android.gms.common.SignInButton
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
                val snackbarHostState = remember { SnackbarHostState() }

                CompositionLocalProvider(LocalSnackbarHostState provides snackbarHostState) {
                    Scaffold(
                        contentWindowInsets = IGNORE_WINDOW_INSETS,
                        snackbarHost = {
                            SnackbarHost(hostState = snackbarHostState) { data ->
                                val visuals = data.visuals as CustomSnackbarVisuals
                                CustomSnackbar(visuals)
                            }
                        },
                    ) { innerPadding ->
                        val authenticator = koinInject<Authenticator>()
                        val userAuthService = koinInject<UserAuthService>()

                        val context = LocalContext.current
                        ObserveAsEvents(flow = authenticator.authActions) { action ->
                            when (action) {
                                is AuthAction.LoginWithGoogle -> {
                                    scope.launch {
                                        userAuthService.loginWithGoogle(context)
                                    }
                                }

                                is AuthAction.Logout -> {
                                    scope.launch {
                                        userAuthService.logout(context)
                                    }
                                }
                            }
                        }

                        if (shareData != null) {
                            ExtractRecipeScreenRoot(
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
    }
}