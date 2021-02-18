package tk.zedlabs.statetest.repository

import tk.zedlabs.statetest.local_db.StoryDao
import tk.zedlabs.statetest.model.Story
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val storyDao: StoryDao
) {
    suspend fun getLatestNews(): List<Story> {
        return remoteDataSource.getRemoteNews()
    }

    suspend fun getCachedNews(): List<Story> {
        return storyDao.getAll()
    }

    companion object {
        const val START_POSITION = 0
        const val CONTENT_SIZE = 20
    }
}
