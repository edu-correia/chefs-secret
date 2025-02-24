package com.educorreia.chefssecrets.login.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel = koinViewModel<LoginViewModel>()
) {
    LoginScreen(
        onEvent = viewModel::onEvent
    )
}

@Composable
fun LoginScreen(
    onEvent: (LoginEvent) -> Unit
) {
    Button(
        onClick = {
            onEvent(LoginEvent.LoginWithGoogle)
        }
    ) {
        Text("Login with Google")
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecipesListScreenPreview() {
    AppTheme {
        LoginScreen(
            onEvent = {}
        )
    }
}