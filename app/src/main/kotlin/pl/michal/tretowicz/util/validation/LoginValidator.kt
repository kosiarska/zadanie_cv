package pl.michal.tretowicz.util.validation

import javax.inject.Inject

class LoginValidator @Inject constructor() {

    fun isCorrect(input: String) = input.isNotBlank()
}