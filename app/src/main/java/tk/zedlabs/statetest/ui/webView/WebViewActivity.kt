package tk.zedlabs.statetest.ui.webView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import tk.zedlabs.statetest.util.stripUrl

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myWebView = WebView(this)
        setContentView(myWebView)


        val intent = intent
        val url = intent.getStringExtra("url")
        title = url?.stripUrl()
        myWebView.loadUrl(url!!)

    }
}