package com.example.colormixer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.colormixer.ui.theme.ColorMixerTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColorMixerTheme {
                Scaffold(topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            Text("Color Mixer")
                        }
                    )
                }) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        ControlManagement()
                    }

                }
            }
        }
    }
}

@Composable
fun ResultingColor(red: Int, green: Int, blue: Int) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color(red, green, blue),
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .size(200.dp)
            .padding(10.dp)
    ) {
        Icon(
            Icons.Outlined.Info,
            contentDescription = "Localized description",
            modifier = Modifier
                .padding(16.dp),
        )
    }
}

@Composable
fun ControlManagement() {
    var red by remember { mutableFloatStateOf(0f) }
    var green by remember { mutableFloatStateOf(0f) }
    var blue by remember { mutableFloatStateOf(0f) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ResultingColor(red.roundToInt(), green.roundToInt(), blue.roundToInt())
        SliderManagement(Color(255, 0, 0), onChanged = { red = it })
        SliderManagement(Color(0, 255, 0), onChanged = { green = it })
        SliderManagement(Color(0, 0, 255), onChanged = { blue = it })
    }
}

@Composable
fun SliderManagement(color: Color, onChanged: (Float) -> Unit) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Slider(
        modifier = Modifier.size(width = 300.dp, height = 50.dp),
        value = sliderPosition,
        onValueChange = {
            sliderPosition = it
            onChanged(it)
        },
        colors = SliderDefaults.colors(
            thumbColor = color,
            activeTrackColor = color,
            inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        steps = 255,
        valueRange = 0f..255f,

        )
    Text(text = sliderPosition.roundToInt().toString(), textAlign = TextAlign.Center)
}