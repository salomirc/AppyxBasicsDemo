package com.example.appyxbasicsdemo.nodes

import android.content.res.Configuration
import android.os.Parcelable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.node
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.pop
import com.bumble.appyx.navmodel.backstack.operation.push
import com.bumble.appyx.navmodel.backstack.transitionhandler.rememberBackstackFader
import com.example.appyxbasicsdemo.ui.theme.AppyxBasicsDemoTheme
import kotlinx.parcelize.Parcelize

class RootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        initialElement = NavTarget.Child1,
        savedStateMap = buildContext.savedStateMap,
    )
) : BaseParentNode<RootNode.NavTarget>(
    navModel = backStack,
    buildContext = buildContext
) {

    sealed class NavTarget : Parcelable {
        @Parcelize
        object Child1 : NavTarget()

        @Parcelize
        object Child2 : NavTarget()

        @Parcelize
        object Child3 : NavTarget()
    }

    @Composable
    override fun View(modifier: Modifier) {
        Surface(
            Modifier.fillMaxSize()
        ) {
            Column {
                // Let's add the children to the composition
                Children(
                    navModel = backStack,
                    modifier = modifier,
                    transitionHandler = rememberBackstackFader { spring(stiffness = Spring.StiffnessLow) }
                )

                // Let's also add some controls so we can test it
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedButton(onClick = { backStack.push(NavTarget.Child1) }) {
                        Text(text = "Push child 1")
                    }
                    OutlinedButton(onClick = { backStack.push(NavTarget.Child2) }) {
                        Text(text = "Push child 2")
                    }
                    OutlinedButton(onClick = { backStack.push(NavTarget.Child3) }) {
                        Text(text = "Push child 3")
                    }
                    OutlinedButton(onClick = { backStack.pop() }) {
                        Text(text = "Pop")
                    }
                }
            }
        }

    }

    override fun resolve(navTarget: NavTarget, buildContext: BuildContext): Node =
        when (navTarget) {
            NavTarget.Child1 -> node(buildContext) { MessageCard(message = "1") }
            NavTarget.Child2 -> node(buildContext) { MessageCard(message = "2") }
            NavTarget.Child3 -> SomeChildNode(buildContext)
        }
}

@Composable
fun MessageCard(message: String) {
    Surface(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(200.dp),
        color = MaterialTheme.colorScheme.tertiary
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Placeholder for child $message"
            )
        }
    }
}


@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewMessageCard() {
    AppyxBasicsDemoTheme {
        MessageCard(message = "1")
    }
}