package pl.michal.tretowicz.util.extension

import timber.log.Timber

/**
 * Created by Michał Trętowicz
 */

fun Throwable.log() {
    Timber.e(this, "Rx Error")
}
