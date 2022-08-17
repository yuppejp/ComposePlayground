package com.example.composeplayground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplayground.ui.theme.ComposePlaygroundTheme

@Immutable
class Typography internal constructor(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val h6: TextStyle,
    val subtitle1: TextStyle,
    val subtitle2: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val button: TextStyle,
    val caption: TextStyle,
    val overline: TextStyle
)
object Texts {
    @Composable
    fun H1(text: String, modifier: Modifier = Modifier) {
        Text(text, modifier, style = MaterialTheme.typography.h1)
    }

    @Composable
    fun H2(text: String, modifier: Modifier = Modifier) {
        Text(text, modifier, style = MaterialTheme.typography.h2)
    }

    @Composable
    fun H3(text: String, modifier: Modifier = Modifier) {
        Text(text, modifier, style = MaterialTheme.typography.h3)
    }

    @Composable
    fun H4(text: String, modifier: Modifier = Modifier) {
        Text(text, modifier, style = MaterialTheme.typography.h4)
    }

    @Composable
    fun H5(text: String, modifier: Modifier = Modifier) {
        Text(text, modifier, style = MaterialTheme.typography.h5)
    }

    @Composable
    fun H6(text: String, modifier: Modifier = Modifier) {
        Text(text, modifier, style = MaterialTheme.typography.h6)
    }

    @Composable
    fun Subtitle1(text: String, modifier: Modifier = Modifier) {
        Text(text, modifier, style = MaterialTheme.typography.subtitle1)
    }

    @Composable
    fun Subtitle2(text: String, modifier: Modifier = Modifier) {
        Text(text, modifier, style = MaterialTheme.typography.subtitle2)
    }

    @Composable
    fun Body1(text: String, modifier: Modifier = Modifier) {
        Text(text, modifier, style = MaterialTheme.typography.body1)
    }

    @Composable
    fun Body2(text: String, modifier: Modifier = Modifier) {
        Text(text, modifier, style = MaterialTheme.typography.body2)
    }

    @Composable
    fun Button(text: String, modifier: Modifier = Modifier) {
        Text(text, modifier, style = MaterialTheme.typography.button)
    }

    @Composable
    fun Caption(text: String, modifier: Modifier = Modifier) {
        Text(text, modifier, style = MaterialTheme.typography.caption)
    }
}

@Composable
fun TypographySample() {
    Column {
        Row(verticalAlignment = Alignment.Bottom) {
            Texts.H1("H1", modifier = Modifier.alignByBaseline())
            Texts.H2("H2", modifier = Modifier.alignByBaseline())
            Texts.H3("H3", modifier = Modifier.alignByBaseline())
            Texts.H4("H4", modifier = Modifier.alignByBaseline())
            Texts.H5("H5", modifier = Modifier.alignByBaseline())
            Texts.H6("H6", modifier = Modifier.alignByBaseline())
        }
        Row(verticalAlignment = Alignment.Bottom) {
            Texts.Subtitle1("Subtitle1", modifier = Modifier.alignByBaseline().padding(2.dp))
            Texts.Subtitle2("Subtitle2", modifier = Modifier.alignByBaseline().padding(2.dp))
            Texts.Body1("Body1", modifier = Modifier.alignByBaseline().padding(2.dp))
            Texts.Body2("Body2", modifier = Modifier.alignByBaseline().padding(2.dp))
            Texts.Button("Button", modifier = Modifier.alignByBaseline().padding(2.dp))
            Texts.Caption("Caption", modifier = Modifier.alignByBaseline().padding(2.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TypographySamplePreview() {
    ComposePlaygroundTheme {
        TypographySample()
    }
}