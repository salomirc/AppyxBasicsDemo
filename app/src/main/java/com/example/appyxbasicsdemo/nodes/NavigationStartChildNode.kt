package com.example.appyxbasicsdemo.nodes

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bumble.appyx.core.modality.BuildContext
import com.example.appyxbasicsdemo.appyx_extensions.DefaultBuildContext

class NavigationStartChildNode(
    buildContext: BuildContext,
    private val onNext: () -> Unit
): BaseNode(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        ButtonMessageCard(
            title = "NavigationStartChildNode",
            buttonText = "Navigate Next",
            action = { onNext() }
        )
    }

}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun NavigationStartChildNodePreview() {
    DefaultBuildContext {
        NavigationStartChildNode(
            this,
            onNext = { }
        ).View(modifier = Modifier)
    }
}