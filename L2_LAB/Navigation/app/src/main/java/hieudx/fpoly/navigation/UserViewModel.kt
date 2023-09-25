package hieudx.fpoly.navigation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var _logedIn = MutableLiveData<Boolean>()
    val loggedIn: LiveData<Boolean> = _logedIn

    init {
        // Init status of logged in by call this function
        _logedIn.value = getLoginStatus(getApplication())
    }

    fun getUserName(): String? {
        return hieudx.fpoly.navigation.getUserName(getApplication())

    }

    fun login(userName: String, password: String): LiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()
        if (userName.isNullOrEmpty().not() &&
            userName == hieudx.fpoly.navigation.getUserName(getApplication()) &&
            password.isNullOrEmpty().not() &&
            password == getPassword(getApplication())
        ) {
            // Login successful
            saveLoginStatus(getApplication(), true)
            _logedIn.value = true
            liveData.value = true
        } else {
            // Login error
            _logedIn.value = false
            liveData.value = false
        }
        return liveData
    }

    fun signUp(userName: String, password: String): LiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()
        if (userName.isNullOrEmpty() || password.isNullOrEmpty()) {
            // Sign up error
            liveData.value = false
        } else {
            // Sign up successful
            saveUsernamePassword(getApplication(), userName, password)
            liveData.value = true
        }
        return liveData
    }

    fun logout(): LiveData<Boolean> {
        saveLoginStatus(getApplication(), false)
        _logedIn.value = false
        return MutableLiveData(true)
    }
}