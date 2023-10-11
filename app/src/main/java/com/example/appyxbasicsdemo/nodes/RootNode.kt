package com.example.appyxbasicsdemo.nodes

import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node

import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.core.node.node
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.pop
import com.bumble.appyx.navmodel.backstack.operation.push
import kotlinx.parcelize.Parcelize

class RootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        initialElement = NavTarget.Child1,
        savedStateMap = buildContext.savedStateMap,
    )
): ParentNode<RootNode.NavTarget>(
    navModel = backStack,
    buildContext = buildContext
) {

    sealed class NavTarget: Parcelable {
        @Parcelize
        object Child1: NavTarget()

        @Parcelize
        object Child2: NavTarget()

        @Parcelize
        object Child3: NavTarget()
    }

    @Composable
    override fun View(modifier: Modifier) {
        Column {
            // Let's add the children to the composition
            Children(
                navModel = backStack
            )

            // Let's also add some controls so we can test it
            Row {
                TextButton(onClick = { backStack.push(NavTarget.Child1) }) {
                    Text(text = "Push child 1")
                }
                TextButton(onClick = { backStack.push(NavTarget.Child2) }) {
                    Text(text = "Push child 2")
                }
                TextButton(onClick = { backStack.push(NavTarget.Child3) }) {
                    Text(text = "Push child 3")
                }
                TextButton(onClick = { backStack.pop() }) {
                    Text(text = "Pop")
                }
            }
        }

    }

    override fun resolve(navTarget: NavTarget, buildContext: BuildContext): Node =
        when (navTarget) {
            NavTarget.Child1 -> node(buildContext) { Text(text = "Placeholder for child 1") }
            NavTarget.Child2 -> node(buildContext) { Text(text = "Placeholder for child 2") }
            NavTarget.Child3 -> node(buildContext) { Text(text = "Placeholder for child 3") }
        }
}