package tk.zedlabs.statetest

import com.squareup.moshi.Json

data class Story (

    @field:Json(name = "id")  var by : String,
    @field:Json(name = "descendants") var descendants : Int,
    @field:Json(name = "id") var id : Int,
    @field:Json(name = "kids") var kids : List<Int>,
    @field:Json(name = "score") var score : Int,
    @field:Json(name = "time") var time : Int,
    @field:Json(name = "title") var title : String,
    @field:Json(name = "type") var type : String,
    @field:Json(name = "url") var url : String

)