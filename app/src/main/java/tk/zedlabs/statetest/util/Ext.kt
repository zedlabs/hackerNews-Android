package tk.zedlabs.statetest.util

import java.net.URI

fun String.stripUrl(): String? {
    val uri: URI? = URI(this)
    return "(" + uri?.authority?.removePrefix("www.") + ")"

}

fun Int.formattedPosition(): String = (this + 1).toString() + "."


fun Pair<Int, String>.pointsAndAuthorString(): String =
    this.first.toString() + " points by " + this.second


