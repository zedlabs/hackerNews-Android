package tk.zedlabs.statetest.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tk.zedlabs.statetest.data.JsonApi
import tk.zedlabs.statetest.local_db.StoryDao
import tk.zedlabs.statetest.local_db.StoryDatabase
import tk.zedlabs.statetest.repository.MainRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()


    @Provides
    @Singleton
    fun providesRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://hacker-news.firebaseio.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesJsonApi(retrofit: Retrofit): JsonApi = retrofit.create(JsonApi::class.java)


    @Provides
    @Singleton
    fun providePostDataSource(jsonApi: JsonApi): MainRepository = MainRepository(jsonApi)

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext application: Context): StoryDatabase {
        return Room
            .databaseBuilder(application, StoryDatabase::class.java, "story-database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideStoryDao(storyDatabase: StoryDatabase): StoryDao {
        return storyDatabase.storyDao()
    }
}