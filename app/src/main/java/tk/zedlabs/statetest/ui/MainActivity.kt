package tk.zedlabs.statetest.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.setContent
import dagger.hilt.android.AndroidEntryPoint
import tk.zedlabs.statetest.model.Story
import tk.zedlabs.statetest.ui.storyList.storyList
import tk.zedlabs.statetest.ui.webView.WebViewActivity


@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme() {
                val news = viewModel.storyList.value
                storyList(list = news)
            }

        }
    }

    /* use navigation component */
//    override fun onItemClick(story: Story) {
//        val i = Intent(this, WebViewActivity::class.java)
//        i.putExtra("url", story.url)
//        startActivity(i)
//    }

}
