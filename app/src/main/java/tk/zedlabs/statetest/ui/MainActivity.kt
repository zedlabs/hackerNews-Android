package tk.zedlabs.statetest.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.setContent
import androidx.room.Room
import dagger.hilt.android.AndroidEntryPoint
import tk.zedlabs.statetest.local_db.StoryDatabase
import tk.zedlabs.statetest.ui.storyList.storyList


@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme() {
                val news = viewModel.storyList.value
                val loadingValue = viewModel.loading.value
                storyList(list = news, loading = loadingValue)
            }
        }
        val room = Room
            .databaseBuilder(application, StoryDatabase::class.java, "story-database")
            .fallbackToDestructiveMigration()
            .build()
        
    }

    /* use navigation component */
//    override fun onItemClick(story: Story) {
//        val i = Intent(this, WebViewActivity::class.java)
//        i.putExtra("url", story.url)
//        startActivity(i)
//    }

}
