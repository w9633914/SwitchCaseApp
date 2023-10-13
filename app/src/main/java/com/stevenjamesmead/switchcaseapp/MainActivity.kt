package com.stevenjamesmead.switchcaseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stevenjamesmead.switchcaseapp.ui.theme.SwitchCaseAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwitchCaseAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

// switchCase IS NOT a composable function
fun switchCase(str: String) : String {
    return str.map {
            ch -> when(ch) {
        in 'a'..'z' -> ch - 32
        in 'A'..'Z' -> ch + 32
        else -> ch
    }}.joinToString("")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var userMessage by remember { mutableStateOf("") }
    var switchCaseMessage by remember { mutableStateOf("") }

    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        Text(
            text = "Please type a word to switch text!!",
            modifier = modifier
        )

        TextField(
            value = userMessage,
            onValueChange = { userMessage = it },
            label = { Text(text = LocalContext.current.getString(R.string.text_field_label)) }
        )

        Button(
            onClick = { switchCaseMessage = switchCase(userMessage) },
            modifier = Modifier.fillMaxWidth()) {

            Text(text = "Update")
        }

        SwitchCaseText(
            text = switchCaseMessage,
            modifier = Modifier.fillMaxWidth().border(width = 1.dp, color = Color.DarkGray).padding(20.dp)
        )

        SwitchCaseText(
            text = switchCaseMessage,
            modifier = Modifier.fillMaxWidth().border(width = 1.dp, color = Color.DarkGray)
        )
    }
}

@Composable
fun SwitchCaseText(
    text: String,
    modifier: Modifier
) {
    Text(
        text = text,
        modifier = Modifier.background(color = Color.White).then(modifier),
        fontSize = 32.sp
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SwitchCaseAppTheme {
        Greeting("Android")
    }
}