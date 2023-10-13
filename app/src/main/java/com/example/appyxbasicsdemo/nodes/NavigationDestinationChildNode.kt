package com.example.appyxbasicsdemo.nodes

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bumble.appyx.core.modality.BuildContext
import com.example.appyxbasicsdemo.appyx_extensions.DefaultBuildContext

class NavigationDestinationChildNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit
): BaseNode(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        ButtonMessageCard(
            title = "NavigationDestinationChildNode",
            buttonText = "Navigate Back",
            action = { onBack() }
        )
    }

}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun NavigationDestinationChildNodePreview() {
    DefaultBuildContext {
        NavigationDestinationChildNode(
            this,
            onBack = { }
        ).View(modifier = Modifier)
    }
}