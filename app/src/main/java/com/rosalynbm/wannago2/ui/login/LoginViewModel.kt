package com.rosalynbm.wannago2.ui.login

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rosalynbm.wannago2.base.BaseViewModel
import timber.log.Timber

class LoginViewModel(app: Application,
                     private val context: Context,
                     private val sharedPref: SharedPreferences
) : BaseViewModel(app) {

    init {
        FirebaseAuth.getInstance()
    }

    fun getAuthMethods(): ArrayList<AuthUI.IdpConfig> {
        return arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
    }

    private val currentUser = MutableLiveData<FirebaseUser>()
    private val authenticationState = MutableLiveData<AuthenticationState>()

    fun getCurrentUser(): LiveData<FirebaseUser> = currentUser
    fun authenticationStateLiveData(): LiveData<AuthenticationState> = authenticationState

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }

    fun authenticationState(): AuthenticationState {
        var authenticated: AuthenticationState = AuthenticationState.UNAUTHENTICATED

        FirebaseAuth.AuthStateListener { firebaseAuth ->
            Timber.d("CurrentUser: ${firebaseAuth.currentUser}")

            authenticated = if (firebaseAuth.currentUser != null)
                AuthenticationState.AUTHENTICATED
            else
                AuthenticationState.UNAUTHENTICATED
        }

        authenticationState.value = authenticated
        return authenticated
    }

    fun setUserAuthenticated(value: Boolean) {
        sharedPref.edit().putBoolean("user_authenticate_state", value).apply()
    }

    fun isUserAuthenticated() = sharedPref.getBoolean("user_authenticate_state", false)

    fun showSnackBar(message: String) {
        showSnackBar.value = message
    }

}
