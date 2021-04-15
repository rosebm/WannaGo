package com.rosalynbm.wannago2.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.rosalynbm.wannago2.BuildConfig
import com.rosalynbm.wannago2.R
import com.rosalynbm.wannago2.base.BaseFragment
import com.rosalynbm.wannago2.base.NavigationCommand
import com.rosalynbm.wannago2.util.Variables
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.coroutines.*
import timber.log.Timber
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment(), View.OnClickListener {

    companion object {
        // Arbitrary request code to identify the request when the result
        // is returned in onActivityResult
        private val SIGN_IN_REQUEST_CODE = 2021
    }

    //use Koin to retrieve the ViewModel instance
    override val _viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init timber
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        loginButton.setOnClickListener(this)
        observeAuthenticationState()
    }

   override fun onResume() {
        super.onResume()

        GlobalScope.launch (Dispatchers.IO) {
            delay(1000)
            withContext(Dispatchers.Main) {
                login_motion_layout?.let {
                    it.transitionToEnd()
                }
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.loginButton -> {
                if (Variables.isNetworkConnected)
                    authenticateAndLogin()
                else
                    Toast.makeText(requireContext(), getString(R.string.no_internet_connection),
                        Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun authenticateAndLogin() {
        when (_viewModel.authenticationState()) {
            LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                Timber.d("User signed in")
                navigatePlacesListFragment()
            }

            LoginViewModel.AuthenticationState.UNAUTHENTICATED -> {
                // not signed in
                Timber.d("User not signed in")

                startActivityForResult(
                    // Get an instance of AuthUI based on the default app
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(_viewModel.getAuthMethods())
                        .build(), SIGN_IN_REQUEST_CODE
                )
            }

            else ->
                Timber.d(getString(R.string.invalid_authentication))
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        // SIGN_IN_REQUEST_CODE is the request code passed into
        // startActivityForResult(...) when starting the sign in flow.
        if (requestCode == SIGN_IN_REQUEST_CODE) {
            val response = IdpResponse.fromResultIntent(data)

            // Successfully signed in
            when(resultCode) {
                Activity.RESULT_OK -> {
                    _viewModel.setUserAuthenticated(true)
                    val idpResponse = IdpResponse.fromResultIntent(data)
                    navigatePlacesListFragment()
                }

                else -> {

                    // Sign in failed
                    if (response == null) {
                        // User pressed back button
                        _viewModel.showSnackBar(getString(R.string.auth_sign_in_cancelled))
                        return
                    }
                    if (response.error?.errorCode == ErrorCodes.NO_NETWORK) {
                        _viewModel.showSnackBar(getString(R.string.no_internet_connection))
                        return
                    } else {
                        _viewModel.showSnackBar(getString(R.string.unknown_error))
                    }
                    Timber.e("Sign-in error: ${response.error}")
                }
            }

        }
    }

    private fun observeAuthenticationState() {
        val userAuthenticated = _viewModel.isUserAuthenticated()

        if (userAuthenticated) {
            navigatePlacesListFragment()
        }
    }

    private fun navigatePlacesListFragment() {
        // Use the navigationCommand live data to navigate between the fragments
        _viewModel.navigationCommand.postValue(
        NavigationCommand.To(LoginFragmentDirections.toPoisListFragment()))
    }

}
