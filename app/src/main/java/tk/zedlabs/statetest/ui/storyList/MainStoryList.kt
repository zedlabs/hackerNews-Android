package tk.zedlabs.statetest.ui.storyList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tk.zedlabs.statetest.R
import tk.zedlabs.statetest.model.Story
import tk.zedlabs.statetest.ui.utilities.progressBar
import tk.zedlabs.statetest.util.formattedPosition
import tk.zedlabs.statetest.util.pointsAndAuthorString
import tk.zedlabs.statetest.util.stripUrl

@Composable
fun storyList(
    list: List<Story>,
    loading: Boolean,
    itemClickEvent: () -> Unit
) {

    Column {
        progressBar(isDisplayed = loading)
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            itemsIndexed(items = list) { index, story ->

                Column {
                    listItem(index, story)
                    Spacer(modifier = Modifier.height(10.dp))
                }

            }
        }
    }

}

@Composable
fun listItem(index: Int, item: Story) {

    Card(
        Modifier.clickable(true, onClick = {})
            .padding(12.dp, 12.dp, 12.dp, 0.dp)
    ) {
        Column(
            Modifier.background(Color.DarkGray)
                .padding(6.dp)
        ) {
            Row {
                Text(
                    text = index.formattedPosition(),
                    fontSize = 16.sp,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)) {
                    Text(
                        text = item.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 16.sp,
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = item.url?.stripUrl()
                                ?: stringResource(R.string.default_story_author),
                            maxLines = 1,
                            fontSize = 12.sp,
                            color = Color.Yellow,
                        )
                        Text(
                            text = Pair(item.score, item.by).pointsAndAuthorString(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 12.sp,
                            color = Color.Gray,
                        )

                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }


}