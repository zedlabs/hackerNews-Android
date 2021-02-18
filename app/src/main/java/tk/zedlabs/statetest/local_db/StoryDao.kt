package tk.zedlabs.statetest.local_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import tk.zedlabs.statetest.model.Story

@Dao
interface StoryDao {

    @Query("SELECT * FROM story")
    suspend fun getAll(): List<Story>

    @Query("SELECT url FROM story WHERE id = :currentId")
    suspend fun getStory(currentId: Int): String

    @Insert
    suspend fun insertAll(vararg stories: Story)

    @Query("DELETE FROM story")
    suspend fun deleteAll()
}