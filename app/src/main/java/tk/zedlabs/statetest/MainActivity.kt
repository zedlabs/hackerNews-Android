package tk.zedlabs.statetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import tk.zedlabs.statetest.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val slAdapter = StoryListAdapter()

        binding.recyclerView.apply {
            adapter = slAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        viewModel.storyList.observe(this){
            slAdapter.submitList(it)
        }

    }
}