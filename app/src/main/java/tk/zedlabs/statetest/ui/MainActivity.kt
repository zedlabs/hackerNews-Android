package tk.zedlabs.statetest.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import tk.zedlabs.statetest.databinding.ActivityMainBinding
import tk.zedlabs.statetest.model.Story
import tk.zedlabs.statetest.repository.MainRepository
import tk.zedlabs.statetest.ui.webView.WebViewActivity
import tk.zedlabs.statetest.util.makeFadeTransition
import tk.zedlabs.statetest.util.remove
import tk.zedlabs.statetest.util.show
import tk.zedlabs.statetest.util.updateProgress


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), StoryListAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    private val storyAdapter = StoryListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
            }
            reloadInternetButton.setOnClickListener { viewModel.loadInitialDetails() }
        }
        observeViewState()
    }

    private fun observeViewState() {
        viewModel.storyListViewState.observe(this) {

            if (it.error != null) binding.reloadInternetButton.show()

            it.stories?.let { storyList ->
                /* remove placeholder */
                binding.updateProgress(storyList.size)

                if (storyList.size == MainRepository.CONTENT_SIZE) {
                    hideLoadingIndicators()
                    /* load data to RV */
                        binding.recyclerView.apply {
                            adapter = storyAdapter.apply { submitList(storyList) }
                        }
                }

            }
        }
    }

    private fun hideLoadingIndicators() {
        binding.reloadInternetButton.remove()
        binding.progressBar.apply {
            binding.root.makeFadeTransition(300)
            remove()
        }
    }

    /* use navigation component */
    override fun onItemClick(story: Story) {
        val i = Intent(this, WebViewActivity::class.java)
        i.putExtra("url", story.url)
        startActivity(i)
    }

}
