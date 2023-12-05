package com.example.textmain

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.textmain.ui.theme.TextMainTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextMainTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainPage()
                }
            }
        }
    }
}

@Composable
fun MainPage(modifier: Modifier = Modifier) {
    var state1 by rememberSaveable {
        mutableStateOf("")
    }
    Column {
        OutlinedTextField(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth(),
            value = state1,
            onValueChange = { state1 = it },
            label = {
                Text("Ori")
            }
        )
        val context = LocalContext.current
        Button(
            onClick = {
                val clipboardService = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val textGot = clipboardService.primaryClip?.getItemAt(0)?.text.toString()
                state1 = textGot
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text("클립")
        }
        OutlinedTextField(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth(),
            value = state1,
            onValueChange = { state1 = it },
            label = {
                Text("Ori")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    TextMainTheme {
        MainPage()
    }
}