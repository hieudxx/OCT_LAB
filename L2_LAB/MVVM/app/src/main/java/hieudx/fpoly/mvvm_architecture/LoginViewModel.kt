package hieudx.fpoly.mvvm_architecture

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField

class LoginViewModel : BaseObservable() {
    private var email: String = ""
    private var pass: String = ""
    var msgLogin = ObservableField<String>()
    var isMsgLogin = ObservableField<Boolean>()

    @get:Bindable
    var Email: String
        get() {
            return email
        }
        set(value) {
            email = value
            notifyPropertyChanged(BR.email)

        }

    @get:Bindable
    var Pass: String
        get() {
            return pass
        }
        set(value) {
            pass = value
            notifyPropertyChanged(BR.pass)
        }

    fun onCliclLogin() {
        val user = User(email, pass)
        isMsgLogin.set(true)
        if (user.isValidEmail() && user.isValidPass()) {
            msgLogin.set("Đăng nhập thành công")
        } else {
            msgLogin.set("Email hoặc mật khẩu không đúng định dạng")
        }
    }
}