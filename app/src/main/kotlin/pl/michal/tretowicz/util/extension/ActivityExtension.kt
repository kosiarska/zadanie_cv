package pl.michal.tretowicz.util.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import com.github.ajalt.timberkt.e
import org.jetbrains.anko.internals.AnkoInternals


/**
 * Created by Michał Trętowicz
 */

fun AppCompatActivity.loadFragment(@IdRes id : Int, fragment: androidx.fragment.app.Fragment, backstack : Boolean) {
    val transaction = this.supportFragmentManager.beginTransaction()

    transaction.replace(id, fragment)
    if (backstack) {
        transaction.addToBackStack(fragment.javaClass.name)
    }
    transaction.commit()
}

fun Activity.finishWithResultOkFromMain(){
    val returnIntent = Intent()
    this.setResult(Activity.RESULT_OK, returnIntent)
    this.finish()
}


inline fun <reified T: Activity> Activity.finishWithResultOk(vararg params: Pair<String, Any>){
    val re = AnkoInternals.createIntent(this, T::class.java , params)
    this.setResult(Activity.RESULT_OK, re)
    this.finish()
}

fun Activity.hideSoftKeyboard() {
    val view: View? = this.getCurrentFocus()
    e { "hideSoftKeyboard $view" }
    if (view != null) {
        val imm: InputMethodManager? = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }
}