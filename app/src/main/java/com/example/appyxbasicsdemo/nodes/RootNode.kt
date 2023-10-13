package com.example.appyxbasicsdemo.nodes

import android.os.Parcelable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.node
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.pop
import com.bumble.appyx.navmodel.backstack.operation.push
import com.bumble.appyx.navmodel.backstack.transitionhandler.rememberBackstackFader
import com.example.appyxbasicsdemo.nodes.RootNode.NavTarget
import com.example.appyxbasicsdemo.nodes.RootNode.NavTarget.Child1
import com.example.appyxbasicsdemo.nodes.RootNode.NavTarget.Child2
import com.example.appyxbasicsdemo.nodes.RootNode.NavTarget.NavigationDestinationChildNode
import com.example.appyxbasicsdemo.nodes.RootNode.NavTarget.NavigationStartChildNode
import com.example.appyxbasicsdemo.nodes.RootNode.NavTarget.SomeChildNode
import kotlinx.parcelize.Parcelize

class RootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        initialElement = Child1,
        savedStateMap = buildContext.savedStateMap,
    )
) : BaseParentNode<NavTarget>(
    navModel = backStack,
    buildContext = buildContext
) {

    sealed class NavTarget : Parcelable {
        @Parcelize
        object Child1 : NavTarget()

        @Parcelize
        object Child2 : NavTarget()

        @Parcelize
        object SomeChildNode : NavTarget()

        @Parcelize
        object NavigationStartChildNode : NavTarget()

        @Parcelize
        object NavigationDestinationChildNode : NavTarget()
    }

    override fun resolve(navTarget: NavTarget, buildContext: BuildContext): Node =
        when (navTarget) {

            Child1 -> node(buildContext) { MessageCard(message = "Placeholder for child 1") }

            Child2 -> node(buildContext) { MessageCard(message = "Placeholder for child 2") }

            SomeChildNode -> SomeChildNode(buildContext)

            NavigationStartChildNode -> NavigationStartChildNode(
                buildContext,
                onNext = { backStack.push(NavigationDestinationChildNode) }
            )

            NavigationDestinationChildNode -> NavigationDestinationChildNode(
                buildContext,
                onBack = { backStack.pop()}
            )
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
                    OutlinedButton(onClick = { backStack.push(Child1) }) {
                        Text(text = "Push child 1")
                    }
                    OutlinedButton(onClick = { backStack.push(Child2) }) {
                        Text(text = "Push child 2")
                    }
                    OutlinedButton(onClick = { backStack.push(SomeChildNode) }) {
                        Text(text = "Push SomeChildNode")
                    }
                    OutlinedButton(onClick = { backStack.push(NavigationStartChildNode) }) {
                        Text(text = "Push NavigationStartChildNode")
                    }
                    OutlinedButton(onClick = { backStack.pop() }) {
                        Text(text = "Pop")
                    }
                }
            }
        }

    }
}