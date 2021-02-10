package tk.zedlabs.statetest.util

import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.transition.TransitionManager
import com.google.android.material.snackbar.Snackbar
import tk.zedlabs.statetest.R
import tk.zedlabs.statetest.databinding.ActivityMainBinding
import java.net.URI

fun ViewGroup.makeFadeTransition(animationDuration: Long) {
    val fade: androidx.transition.Fade = androidx.transition.Fade()
    fade.apply { duration = animationDuration }
    TransitionManager.beginDelayedTransition(this, fade)
}

fun String.stripUrl(): String? {
    val uri: URI? = URI(this)
    return uri?.authority?.removePrefix("www.")

}

fun View.show(){
    if(this.visibility == View.GONE || this.visibility == View.INVISIBLE)
        this.visibility = View.VISIBLE
}

fun View.remove(){
        this.visibility = View.GONE
}

fun Int.formattedPosition(): String = (this + 1).toString() + "."


fun Pair<Int, String>.pointsAndAuthorString(): String =
    this.first.toString() + " points by " + this.second

fun View.showSnackBar(text: String) {
    Snackbar.make(this, R.string.no_connection, Snackbar.LENGTH_INDEFINITE)
        .show()
}

fun ActivityMainBinding.updateProgress(size: Int){
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        this.progressBar.setProgress(size*5, true)
    }
}

