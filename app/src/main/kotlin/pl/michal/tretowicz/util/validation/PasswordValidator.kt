package pl.michal.tretowicz.util.validation

import javax.inject.Inject

class PasswordValidator @Inject constructor()  {

    companion object {
        private const val MIN_PASSWORD_CHARS_COUNT = 8
    }

      fun isCorrect(input: String) =
            input.isNotBlank() && input.length >= MIN_PASSWORD_CHARS_COUNT
}