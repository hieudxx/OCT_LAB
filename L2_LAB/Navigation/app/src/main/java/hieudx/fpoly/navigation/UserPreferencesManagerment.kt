package hieudx.fpoly.navigation

import android.content.Context
import androidx.core.content.edit

private const val PREF_NAME = "com.yellowcode.navigationsample.shared_preferences"
private const val USERNAME_KEY = "USERNAME_KEY"
private const val PASSWORD_KEY = "PASSWORD_KEY"
private const val LOGGED_IN_KEY = "LOGGED_IN_KEY"

fun saveUsernamePassword(context: Context, userName: String, password: String) {
    val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    sharedPref.edit(commit = false) {
        putString(USERNAME_KEY, userName)
        putString(PASSWORD_KEY, password)
    }
}

fun saveLoginStatus(context: Context, loggedIn: Boolean) {
    val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    sharedPref.edit(commit = false) {
        putBoolean(LOGGED_IN_KEY, loggedIn)
    }
}

fun getUserName(context: Context): String? {
    val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    return sharedPref.getString(USERNAME_KEY, "")
}

fun getPassword(context: Context): String? {
    val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    return sharedPref.getString(PASSWORD_KEY, "")
}

fun getLoginStatus(context: Context): Boolean {
    val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    return sharedPref.getBoolean(LOGGED_IN_KEY, false)
}


