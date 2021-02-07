package tk.zedlabs.statetest.ui

import tk.zedlabs.statetest.model.Story

data class StoryListViewState(
    val isComplete: Boolean,
    val error: Error?,
    val stories: List<Story>?
)