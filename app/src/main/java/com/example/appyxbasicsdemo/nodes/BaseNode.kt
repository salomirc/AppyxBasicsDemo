package com.example.appyxbasicsdemo.nodes

import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.EmptyNodeView
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.NodeView
import com.bumble.appyx.core.plugin.Plugin
import com.example.appyxbasicsdemo.appyx_extensions.printNodeHierarchy

abstract class BaseNode(
    buildContext: BuildContext,
    view: NodeView = EmptyNodeView,
    plugins: List<Plugin> = emptyList()
) : Node(buildContext, view, plugins) {
    init {
        printNodeHierarchy()
    }
}