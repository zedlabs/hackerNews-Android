package tk.zedlabs.statetest.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "story")
data class Story(

    @field:Json(name = "by")
    var by: String,

    @field:Json(name = "descendants")
    var descendants: Int,

    @PrimaryKey
    @field:Json(name = "id")
    var id: Int,

    @field:Json(name = "score")
    var score: Int,

    @field:Json(name = "time")
    var time: Int,

    @field:Json(name = "title")
    var title: String,

    @field:Json(name = "type")
    var type: String,

    @field:Json(name = "url")
    var url: String?

)