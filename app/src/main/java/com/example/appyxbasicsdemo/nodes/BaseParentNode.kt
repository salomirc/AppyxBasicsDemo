package com.example.appyxbasicsdemo.nodes

import com.bumble.appyx.Appyx
import com.bumble.appyx.core.children.ChildAware
import com.bumble.appyx.core.children.ChildAwareImpl
import com.bumble.appyx.core.children.ChildEntry
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.navigation.NavModel
import com.bumble.appyx.core.node.EmptyParentNodeView
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.core.node.ParentNodeView
import com.bumble.appyx.core.plugin.Plugin
import com.example.appyxbasicsdemo.appyx_extensions.printNodeHierarchy

abstract class BaseParentNode<NavTarget : Any>(
    navModel: NavModel<NavTarget, *>,
    buildContext: BuildContext,
    view: ParentNodeView<NavTarget> = EmptyParentNodeView(),
    childKeepMode: ChildEntry.KeepMode = Appyx.defaultChildKeepMode,
    childAware: ChildAware<ParentNode<NavTarget>> = ChildAwareImpl(),
    plugins: List<Plugin> = listOf(),
) : ParentNode<NavTarget>(
    navModel = navModel,
    buildContext = buildContext,
    view = view,
    childKeepMode = childKeepMode,
    childAware = childAware,
    plugins = plugins,
) {
    init {
        printNodeHierarchy()
    }
}