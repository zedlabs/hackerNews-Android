package tk.zedlabs.statetest.util

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.view.ViewGroup
import androidx.transition.TransitionManager
import com.google.android.material.snackbar.Snackbar
import java.net.URI

fun ViewGroup.makeFadeTransition(animationDuration: Long) {
    val fade: androidx.transition.Fade = androidx.transition.Fade()

    fade.apply {
        duration = animationDuration
    }
    TransitionManager.beginDelayedTransition(this, fade)
}

fun String.stripUrl(): String {

    val uri = URI(this)
    return uri.authority.removePrefix("www.")

}

fun Int.formattedPosition(): String {

    return (this + 1).toString() + "."

}

fun Pair<Int, String>.pointsAndAuthorString(): String {
    return this.first.toString() + " points by " + this.second
}

fun Context.isConnectedToNetwork(): Boolean {
    val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

    return manager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
}

fun View.showSnackBar(text: String) {
    Snackbar.make(
        this,
        text,
        Snackbar.LENGTH_INDEFINITE
    ).show()
}