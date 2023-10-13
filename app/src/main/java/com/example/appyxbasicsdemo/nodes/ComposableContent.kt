package com.example.appyxbasicsdemo.nodes

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.appyxbasicsdemo.ui.theme.AppyxBasicsDemoTheme

@Composable
fun MessageCard(
    message: String,
    content: @Composable () -> Unit = { }
) {
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
                text = message
            )
            content()
        }
    }
}

@Composable
fun ButtonMessageCard(
    title: String,
    buttonText: String,
    action: () -> Unit
) {
    MessageCard(message = title) {
        OutlinedButton(
            onClick = { action() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = buttonText)
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
        MessageCard(message = "Placeholder for child 1")
    }
}


@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewButtonMessageCard() {
    AppyxBasicsDemoTheme {
        ButtonMessageCard(
            title = "Placeholder for child 1",
            buttonText = "Navigate Next",
            action = { }
        )
    }
}