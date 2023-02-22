package com.example.lemonade

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp() {
    LemonadeImageAndText(
        Modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}

@Composable
fun LemonadeImageAndText(modifier: Modifier = Modifier) {
    var buttonCounter by remember { mutableStateOf(1) }
    var squeezeCounter by remember { mutableStateOf(1) }
    val lemonadeData = LemonadeData(buttonCounter)
    val lemonadeText = lemonadeData.lemonadeText
    val lemonadeImage = lemonadeData.lemonadeImage
    val contentDescription = lemonadeData.contentDescription
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = stringResource(id = lemonadeText))
        Button(
            onClick = {
                //(buttonCounter < 4) buttonCounter + 1 else 1
                buttonCounter = when(buttonCounter){
                    1 -> {
                        squeezeCounter = (2..4).random()
                        buttonCounter + 1
                    }
                    2 -> {
                        squeezeCounter--
                        if(squeezeCounter == 0){
                            buttonCounter + 1
                        } else {
                            2
                        }
                    }
                    3 -> buttonCounter + 1
                    else -> 1
                }

                Log.d("TAG", "Button pressed.  Number is $buttonCounter")
            },
        ) {
            Image(
                painter = painterResource(id = lemonadeImage),
                contentDescription = stringResource(id = contentDescription)
            )
        }
    }
}

private class LemonadeData(val buttonCounter: Int) {
    var lemonadeText: Int = when (buttonCounter) {
        1 -> R.string.first_text
        2 -> R.string.second_text
        3 -> R.string.third_text
        else -> R.string.fourth_text
    }
    var lemonadeImage: Int = when (buttonCounter) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    var contentDescription: Int = when (buttonCounter) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }
}


