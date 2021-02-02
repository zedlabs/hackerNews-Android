package tk.zedlabs.statetest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tk.zedlabs.statetest.databinding.ListItemBinding

class StoryListAdapter : ListAdapter<Story, StoryListAdapter.StoryListViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryListViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class StoryListViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(story: Story) {
            binding.apply {
                binding.titleTextView.text = story.title
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Story>() {

        override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean =
            oldItem == newItem
    }
}
