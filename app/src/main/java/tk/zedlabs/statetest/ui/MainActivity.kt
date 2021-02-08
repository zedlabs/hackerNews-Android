package tk.zedlabs.statetest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import tk.zedlabs.statetest.R
import tk.zedlabs.statetest.databinding.ActivityMainBinding
import tk.zedlabs.statetest.model.Story
import tk.zedlabs.statetest.ui.webView.WebViewActivity
import tk.zedlabs.statetest.util.isConnectedToNetwork
import tk.zedlabs.statetest.util.makeFadeTransition
import tk.zedlabs.statetest.util.showSnackBar

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), StoryListAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    private val storyAdapter = StoryListAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel.loadInitialDetails()

        if (!this.isConnectedToNetwork())  noInternetConnection()

        binding.recyclerView.apply {
            adapter = this@MainActivity.storyAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        observeViewState()
    }

    private fun observeViewState() {
        viewModel.storyListViewState.observe(this) {

            if (it.error != null) noInternetConnection()

            it.stories?.let { storyList ->
                //remove placeholder
                binding.progressBar.visibility = View.GONE
                /* load data to RV */
                if (storyList.isNotEmpty()) {
                    binding.recyclerView.apply {
                        this.makeFadeTransition(600)
                        adapter = storyAdapter.apply { submitList(storyList) }
                    }
                } else {
                    Toast.makeText(this, "Failed to Load Data", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    /* use navigation component */
    override fun onItemClick(story: Story) {
        val i = Intent(this, WebViewActivity::class.java)
        i.putExtra("url", story.url)
        startActivity(i)
    }

    private fun noInternetConnection() {
        binding.apply {
            root.showSnackBar(getString(R.string.no_connection))
            progressBar.visibility = View.GONE
        }
    }
}