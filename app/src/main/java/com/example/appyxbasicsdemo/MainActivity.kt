package com.example.appyxbasicsdemo

import android.os.Bundle
import androidx.activity.compose.setContent
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeComponentActivity
import com.example.appyxbasicsdemo.nodes.RootNode
import com.example.appyxbasicsdemo.ui.theme.AppyxBasicsDemoTheme

class MainActivity : NodeComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppyxBasicsDemoTheme {
                NodeHost(integrationPoint = appyxIntegrationPoint) {
                    RootNode(buildContext = it)
                }
            }
        }
    }
}