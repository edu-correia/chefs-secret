package com.educorreia.chefssecrets.core.data.services.firebase_auth

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.educorreia.chefssecrets.BuildConfig
import com.educorreia.chefssecrets.core.data.domain.interfaces.UserAuthService
import com.educorreia.chefssecrets.core.data.domain.models.User
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class FirebaseUserAuth : UserAuthService {
    private lateinit var auth: FirebaseAuth

    init {
        auth = Firebase.auth
    }

    override fun isUserAuthenticated(): Boolean {
        return auth.currentUser != null
    }

    override fun getAuthenticatedUser(): User? = auth.currentUser?.run {
        User(
            id = uid,
            name = displayName ?: "",
            email = email ?: "",
            photoUrl = photoUrl.toString(),
        )
    }

    override suspend fun loginWithGoogle(context: Context, onLogin: () -> Unit) {
        val credential = getGoogleCredential(context)

        if (isGoogleIdCredential(credential)) {
            authenticateWithFirebase(credential, onLogin)
        } else {
            // Invalid auth type
        }
    }

    private suspend fun getGoogleCredential(context: Context): Credential {
        val credentialManager = CredentialManager.create(context)

        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(BuildConfig.FIREBASE_WEB_CLIENT_ID)
            .setFilterByAuthorizedAccounts(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val credential = credentialManager
            .getCredential(context, request)
            .credential

        return credential
    }

    private fun isGoogleIdCredential(credential: Credential): Boolean {
        val isCustomCredential = credential is CustomCredential
        val isTokenOfTypeGoogleId = credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        return isCustomCredential && isTokenOfTypeGoogleId
    }

    private suspend fun authenticateWithFirebase(credential: Credential, onLogin: () -> Unit) {
        val idTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
        val idToken = idTokenCredential.idToken
        val authCredential = GoogleAuthProvider.getCredential(idToken, null)
        val user = auth.signInWithCredential(authCredential).await().user
        println("EDUAAA: ${user?.displayName}")

        onLogin()
    }

    override suspend fun logout(context: Context, onLogout: () -> Unit) {
        if (!isUserAuthenticated()) return

        auth.signOut()

        val credentialManager = CredentialManager.create(context)
        val clearRequest = ClearCredentialStateRequest()
        credentialManager.clearCredentialState(clearRequest)

        onLogout()
    }
}