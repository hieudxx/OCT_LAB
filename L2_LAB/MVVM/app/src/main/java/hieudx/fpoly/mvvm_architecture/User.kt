package hieudx.fpoly.mvvm_architecture

import android.text.TextUtils
import android.util.Patterns

class User(private var email: String, private var pass: String) {
    fun isValidEmail(): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPass(): Boolean {
        return !TextUtils.isEmpty(pass) && pass.length >= 6
    }
}