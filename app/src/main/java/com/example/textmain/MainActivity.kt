package com.example.textmain

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

//data class TwoStates(val firstState: String, val secondState: String)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainPage(modifier: Modifier = Modifier) {
    var state1 by rememberSaveable {
        mutableStateOf("")
    }
    var state2 by rememberSaveable {
        mutableStateOf("")
    }
    var isFlowRowShow by rememberSaveable {
        mutableStateOf(false)
    }
//    var twoStates by rememberSaveable {
//        mutableStateOf(TwoStates())
//    }


    // UI State 패턴. 화면 UI를 담당하는 클래스 (데이터 클래스)를 만들어서 다 집어넣는 방식.
    Column {
        OutlinedTextField(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth(),
            value = state1,
            onValueChange = { state1 = it },
            label = {
                Text("Before")
            }
        )
        val context = LocalContext.current
        FlowRow {
            Box(
                modifier = Modifier.height(40.dp).padding(5.dp).background(Color.DarkGray)
            ) {
                Button(
                    onClick = {
                        val clipboardService =
                            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val textGot = clipboardService.primaryClip?.getItemAt(0)?.text.toString()
                        state1 = textGot
                    }
                ) {
                    Text("클립", fontSize = 9.sp)
                }
            }
            Box(
                modifier = Modifier.height(40.dp).padding(5.dp).background(Color.DarkGray)
            ) {
                Button(
                    onClick = {
                        state2 = state1.replace("/", "/ ")
                    }
                ) {
                    Text("짤라")
                }
            }
            Box(
                modifier = Modifier.height(40.dp).padding(5.dp).background(Color.DarkGray)
            ) {
                Button(
                    onClick = {
                        isFlowRowShow = !isFlowRowShow
                    }
                ) {
                    Text("새끼버튼")
                }
            }
            Box(
                modifier = Modifier.height(40.dp).padding(5.dp).background(Color.DarkGray)
            ) {
                Button(
                    onClick = {
                        var result = ""
                        var isFirstNonAlphanum = true
                        state1.forEach {
                            when {
                                it.isLetterOrDigit() -> {
                                    isFirstNonAlphanum = true
                                    result += it
                                }
                                isFirstNonAlphanum -> {
                                    isFirstNonAlphanum = false
                                    result += " $it"
                                }
                                else -> result += it
                            }
                        }
                        state2 = result
                    }
                ) {
                    Text("AN")
                }
            }
        }
        if (isFlowRowShow) {
            FlowRow {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color.Blue)
                )
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color.Red)
                )
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color.Cyan)
                )
            }
        }
        TextField(
            value = state2,
            onValueChange = {},
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth()
                .border(width = 1.dp, Color.Blue)
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