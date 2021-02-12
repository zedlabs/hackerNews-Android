package tk.zedlabs.statetest.ui.utilities

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun progressBar(isDisplayed: Boolean) {
    if (isDisplayed) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
}