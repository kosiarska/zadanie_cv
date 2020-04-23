package pl.michal.tretowicz.data

import android.text.TextUtils
import javax.inject.Singleton

/**
 * Created by Michał Trętowicz
 */
@Singleton
class FieldsValidator {

    fun validateEmail(target: String): Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    fun validatePassword(target: String): Boolean {
        return !TextUtils.isEmpty(target) && target.length >= 8
    }

    fun validateRepeatPassword(pass: String, passRepeat: String): Boolean {
        return pass == passRepeat
    }

    fun validateLogin(target: String): Boolean {
        return !TextUtils.isEmpty(target)
    }

}
