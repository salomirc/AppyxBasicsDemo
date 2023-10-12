package com.example.appyxbasicsdemo.nodes

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bumble.appyx.core.modality.BuildContext
import com.example.appyxbasicsdemo.appyx_extensions.DefaultBuildContext

class SomeChildNode(buildContext: BuildContext): BaseNode(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        MessageCard(message = "3")
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SomeChildNodePreview() {
    DefaultBuildContext {
        SomeChildNode(this).View(modifier = Modifier)
    }
}