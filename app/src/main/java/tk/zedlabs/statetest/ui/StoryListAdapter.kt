package tk.zedlabs.statetest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tk.zedlabs.statetest.model.Story
import tk.zedlabs.statetest.databinding.ListItemBinding
import tk.zedlabs.statetest.util.formattedPosition
import tk.zedlabs.statetest.util.pointsAndAuthorString
import tk.zedlabs.statetest.util.stripUrl

class StoryListAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Story, StoryListAdapter.StoryListViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryListViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class StoryListViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(getItem(adapterPosition))
            }
        }

        fun bind(story: Story) {
            binding.apply {
                titleTextView.text = story.title
                rankTextView.text = adapterPosition.formattedPosition()
                linkTextView.text = story.url?.stripUrl()
                infoTextView.text = Pair(story.score, story.by).pointsAndAuthorString()
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(story: Story)
    }

    class DiffCallback : DiffUtil.ItemCallback<Story>() {

        override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean =
            oldItem == newItem
    }
}
