package com.example.learning_rate

import android.Manifest
import android.content.Context
import android.content.Intent
import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learning_rate.ui.theme.Learning_rateTheme
import java.nio.file.WatchEvent
import kotlin.contracts.contract

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Learning_rateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding).offset(
                            x = 0.dp, y = -20.dp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var msg by remember { mutableStateOf("enter") }
    var context = LocalActivity.current

    Box(

        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = msg,
            modifier = modifier.width(60.dp).height(60.dp)
                .offset(x = 60.dp, y = -60.dp)
            ,
            fontSize = 25.sp
        )
        Box(
            modifier = Modifier.height(50.dp)
                .width(100.dp).offset(x = 50.dp, y = 30.dp)
                .background( brush = Brush.linearGradient(colors = listOf(Color.Red,Color.Blue))
                )
                .clickable{msg = printable()}
            ,
            contentAlignment = Alignment.Center

        )
        {
            Text(
                modifier = Modifier.padding(14.dp).background(color = Color.Blue,
                    RoundedCornerShape(16.dp)
                ),

                text = "click it"
            )

        }
        Box(
            modifier = Modifier.height(30.dp).width(60.dp).offset(x = -60.dp, y = 40.dp)
                .background(color = Color.Blue)
                .clickable{
                    var intent = Intent(context, MainActivity2::class.java)
                    context?.startActivity(intent)

                }
        )


    }
}

fun printable(): String = "this is it"
//@Composable

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Learning_rateTheme {
        Greeting("Android")
    }
}