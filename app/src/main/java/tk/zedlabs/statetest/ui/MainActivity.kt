package tk.zedlabs.statetest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import tk.zedlabs.statetest.databinding.ActivityMainBinding
import tk.zedlabs.statetest.util.makeFadeTransition

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    private val storyAdapter = StoryListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadInitialDetails()

        binding.recyclerView.apply {
            adapter = this@MainActivity.storyAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        observeViewState()
    }

    private fun observeViewState() {
        viewModel.storyListViewState.observe(this){
            it.stories?.let { storyList ->
                //remove placeholder
                binding.progressBar.visibility = View.GONE
                /* load data to RV */
                if(storyList.isNotEmpty()){
                    binding.recyclerView.apply {
                        this.makeFadeTransition(600)
                        adapter = storyAdapter.apply { submitList(storyList) }
                    }
                }else{
                    Toast.makeText(this, "Failed to Load Data", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}