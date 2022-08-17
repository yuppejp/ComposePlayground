package com.example.composeplayground

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeplayground.ui.theme.ComposePlaygroundTheme

// マテリアルアイコン一覧
// https://fonts.google.com/icons?icon.set=Material+Icons

@Composable
fun LikeButton() {
    Button(onClick = {}) {
        Icon(
            imageVector = Icons.Filled.ThumbUp,
            contentDescription = null
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text("Like")
    }
}

@Preview(showBackground = true)
@Composable
fun LikeButtonPreview() {
    ComposePlaygroundTheme {
        LikeButton()
    }
}
