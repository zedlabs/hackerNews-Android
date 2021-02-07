package tk.zedlabs.statetest

data class StoryListViewState(
    val isComplete: Boolean,
    val error: Error?,
    val stories: List<Story>?
)