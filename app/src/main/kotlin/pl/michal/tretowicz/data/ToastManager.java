package pl.michal.tretowicz.data;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.StringRes;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;

/**
 * Created by Michał Trętowicz
 */
public class ToastManager {

    public Activity context;

    @Inject
    public ToastManager(Activity context) {
        this.context = context;
    }

    public void success(String message) {
        Toasty.success(context, message, Toast.LENGTH_SHORT, true).show();
    }

    public void error(String message) {
        Toasty.error(context, message, Toast.LENGTH_SHORT, true).show();
    }

    public void success(@StringRes int message) {
        success(context.getString(message));
    }

    public void error(@StringRes int message) {
        error(context.getString(message));
    }
}
