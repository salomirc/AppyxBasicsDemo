package com.example.appyxbasicsdemo.appyx_extensions

import android.util.Log
import androidx.compose.runtime.Composable
import com.bumble.appyx.core.modality.AncestryInfo
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.example.appyxbasicsdemo.ui.theme.AppyxBasicsDemoTheme

@Composable
inline fun DefaultBuildContext(crossinline contents: @Composable BuildContext.() -> Unit) {
    AppyxBasicsDemoTheme {
        BuildContext.root(savedStateMap = null).contents()
    }
}

fun Node.printNodeHierarchy(topToBottom: Boolean = true) {
    fun Node.ancestor(): Node? {
        return when(val ancestryInfo = this.ancestryInfo) {
            is AncestryInfo.Root -> null
            is AncestryInfo.Child -> ancestryInfo.anchor
        }
    }

    val node = this

    val nodes = mutableListOf(node)
    var ancestor: Node? = node.ancestor()
    while (ancestor != null) {
        nodes.add(ancestor)
        ancestor = ancestor.ancestor()
    }

    val result = nodes
        .let { if (topToBottom) it.asReversed() else it }
        .joinToString(" > ", prefix = "Nodes: ") { it::class.java.simpleName }

    Log.d("Appyx_Navigation", result)
}