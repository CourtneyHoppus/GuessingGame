package com.example.guessinggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.guessinggame.ui.theme.GuessingGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessingGameTheme {
                GuessingGame()
            }
        }
    }
}

@Composable
fun GuessingGame() {
    var currentStep by remember { mutableStateOf(1) }
    var suit by remember { mutableStateOf(0) }
    var suitDrawable by remember { mutableStateOf(0) }
    var suitDescription by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {
            1 -> ImageAndText(
                textLabelResourceId = R.string.welcome,
                drawableResourceId = R.drawable.symbols,
                contentDescriptionResourceId = R.string.symbols,
                onImageClick = {
                    currentStep = 2
                    suit = (1..4).random()
                    when (suit) {
                        1 -> {
                            suitDrawable = R.drawable.club
                            suitDescription = R.string.club
                        }
                        2 -> {
                            suitDrawable = R.drawable.spade
                            suitDescription = R.string.spade
                        }
                        3 -> {
                            suitDrawable = R.drawable.heart
                            suitDescription = R.string.heart
                        }
                        4 -> {
                            suitDrawable = R.drawable.diamond
                            suitDescription = R.string.diamond
                        }
                    }
                }
            )
            2 -> ImageAndText(
                textLabelResourceId = R.string.guess,
                drawableResourceId = suitDrawable,
                contentDescriptionResourceId = suitDescription,
                onImageClick = { currentStep = 1 }
            )
        }
    }
}

@Composable
fun ImageAndText(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.height(160.dp)
            ) {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = stringResource(contentDescriptionResourceId),
                    contentScale = ContentScale.Fit,
                    modifier = modifier
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
            Text(text = stringResource(textLabelResourceId), modifier = Modifier.padding(36.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GuessingGameTheme {
        GuessingGame()
    }
}
