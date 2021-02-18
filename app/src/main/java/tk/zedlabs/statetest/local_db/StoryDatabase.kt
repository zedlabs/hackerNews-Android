package tk.zedlabs.statetest.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import tk.zedlabs.statetest.model.Story

@Database(entities = [Story::class], version = 1)
abstract class StoryDatabase : RoomDatabase() {
    abstract fun storyDao(): StoryDao
}