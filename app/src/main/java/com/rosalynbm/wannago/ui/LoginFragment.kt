package com.rosalynbm.wannago.ui

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.snackbar.Snackbar
import com.rosalynbm.wannago.BuildConfig
import com.rosalynbm.wannago.R
import com.rosalynbm.wannago.base.BaseFragment
import com.rosalynbm.wannago.base.BaseViewModel
import com.rosalynbm.wannago.base.NavigationCommand
import com.rosalynbm.wannago.util.Variables
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.android.ext.android.inject
import timber.log.Timber
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment(), View.OnClickListener {

    companion object {
        fun newInstance() = LoginFragment()
        // Arbitrary request code to identify the request when the result is returned in onActivityResult
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

        //viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel

        // init timber
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        loginButton.setOnClickListener(this)
        observeAuthenticationState()
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

                //ros startReminderActivity()
                //ros finish()
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
        // SIGN_IN_REQUEST_CODE is the request code passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == SIGN_IN_REQUEST_CODE) {
            val response = IdpResponse.fromResultIntent(data)

            // Successfully signed in
            when(resultCode) {
                Activity.RESULT_OK -> {
                    _viewModel.setUserAuthenticated(true)

                    val idpResponse = IdpResponse.fromResultIntent(data)
                    /*startActivity(
                        Intent(this, RemindersActivity::class.java)
                            .putExtra("my_token", idpResponse?.idpToken)
                    )
                    finish()*/
                }

                else -> {

                    // Sign in failed
                    if (response == null) {
                        // User pressed back button
                        loginButton.snack(R.string.auth_sign_in_cancelled, Snackbar.LENGTH_LONG,{})
                        return
                    }
                    if (response.error?.errorCode == ErrorCodes.NO_NETWORK) {
                        loginButton.snack(R.string.no_internet_connection, Snackbar.LENGTH_LONG,{})
                        return
                    } else {
                        loginButton.snack(R.string.unknown_error, Snackbar.LENGTH_LONG,{})
                    }
                    Timber.e("Sign-in error: ${response.error}")
                }
            }

        }
    }

    private fun observeAuthenticationState() {
        val userAuthenticated = _viewModel.isUserAuthenticated()

        if (userAuthenticated) {
            //ros startReminderActivity()
            navigatePlacesListFragment()
        }
    }

    private inline fun View.snack(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
        val snack = Snackbar.make(this, messageRes, length)
        snack.f()
        snack.show()
    }

    private fun navigatePlacesListFragment() {
        //use the navigationCommand live data to navigate between the fragments
       /* //ros  viewModel.navigationCommand.postValue(
            NavigationCommand.To(
                ReminderListFragmentDirections.toSaveReminder()
            )
        )*/
    }

}