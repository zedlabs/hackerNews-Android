package tk.zedlabs.statetest.local_db

import androidx.room.Dao
import androidx.room.Query
import tk.zedlabs.statetest.model.Story

@Dao
interface StoryDao {

    @Query("SELECT * FROM story")
    fun getAll(): List<Story>

    @Query("SELECT url FROM story WHERE id = :currentId")
    fun getStory(currentId: Int): String
}