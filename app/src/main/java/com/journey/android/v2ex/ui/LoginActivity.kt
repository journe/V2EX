package com.journey.android.v2ex.ui

import android.os.AsyncTask
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.journey.android.v2ex.R
import com.journey.android.v2ex.bean.js.LoginBean
import com.journey.android.v2ex.net.GetLoginTask
import com.journey.android.v2ex.utils.ImageLoader
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private var mAuthTask: UserLoginTask? = null
    private lateinit var mLoginBean: LoginBean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login_password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        sign_in_button.setOnClickListener {
//            attemptLogin()
            ImageLoader.displayImage(this@LoginActivity, mLoginBean.genCaptcha(),
                    login_captcha_iv)
        }
        login_refresh.setOnRefreshListener {
            doGetLoginTask()
        }
//        val retrofit = Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .addConverterFactory(SimpleXmlConverterFactory.create())
//                .build()!!
//        val service = retrofit.create(GetAPIService::class.java)
//        service.login().enqueue(object : Callback<String> {
//            override fun onFailure(call: Call<String>?, t: Throwable?) {
//                t?.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<String>?, response: Response<String>?) {
//                Logger.d(response?.body().toString())
//            }
//        })
        doGetLoginTask()
    }

    private fun doGetLoginTask() {
        object : GetLoginTask() {
            override fun onStart() {
                showProgress(true)
            }

            override fun onFinish(loginBean: LoginBean) {
                showProgress(false)
                mLoginBean = loginBean
            }
        }.execute()
    }

    private fun attemptLogin() {
        if (mAuthTask != null) {
            return
        }

        // Reset errors.
        login_account.error = null
        login_password.error = null

        // Store values at the time of the login attempt.
        val emailStr = login_account.text.toString()
        val passwordStr = login_password.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordStr) && !isPasswordValid(passwordStr)) {
            login_password.error = getString(R.string.error_invalid_password)
            focusView = login_password
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emailStr)) {
            login_account.error = getString(R.string.error_field_required)
            focusView = login_account
            cancel = true
        } else if (!isEmailValid(emailStr)) {
            login_account.error = getString(R.string.error_invalid_email)
            focusView = login_account
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
            mAuthTask = UserLoginTask(emailStr, passwordStr)
            mAuthTask!!.execute(null as Void?)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        //TODO: Replace this with your own logic
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 4
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    private fun showProgress(show: Boolean) {
        login_refresh.isRefreshing = show
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    inner class UserLoginTask internal constructor(private val mEmail: String, private val mPassword: String) : AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void): Boolean? {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                return false
            }

            return DUMMY_CREDENTIALS
                    .map { it.split(":") }
                    .firstOrNull { it[0] == mEmail }
                    ?.let {
                        // Account exists, return true if the password matches.
                        it[1] == mPassword
                    }
                    ?: true
        }

        override fun onPostExecute(success: Boolean?) {
            mAuthTask = null
            showProgress(false)

            if (success!!) {
                finish()
            } else {
                login_password.error = getString(R.string.error_incorrect_password)
                login_password.requestFocus()
            }
        }

        override fun onCancelled() {
            mAuthTask = null
            showProgress(false)
        }
    }

    companion object {

        /**
         * A dummy authentication store containing known user names and passwords.
         * TODO: remove after connecting to a real authentication system.
         */
        private val DUMMY_CREDENTIALS = arrayOf("foo@example.com:hello", "bar@example.com:world")
    }
}
