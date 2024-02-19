package com.example.recomposetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recomposetest.ui.theme.RecomposeTestTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecomposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val uiState by viewModel.uiState.collectAsState()
                    CountDownView(uiState = uiState, onClick = viewModel::buttonClick)
                }
            }
        }
    }
}

@Composable
fun CountDownView(uiState: UIState, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CoolTitleView(titleState = uiState.titleState)
        CountDownView(countDownState = uiState.countDownState, onClick = onClick)
    }
}

@Composable
fun CoolTitleView(titleState: TitleState) {
    when (titleState) {
        is TitleState.NoTitle -> {
            Text(text = "NO Title")
        }
        is TitleState.CoolTitle -> {
            Text(text = titleState.title)
        }
    }
}

@Composable
fun CountDownView(countDownState: CountDownState, onClick: () -> Unit) {
    when (countDownState) {
        is CountDownState.CountDown -> {
            Text(text = countDownState.count.toString())
        }
        is CountDownState.StartButton -> {
            Button(modifier = Modifier.height(50.dp), onClick = onClick) {
                Text(text = "Click to CountDown")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecomposeTestTheme {
        CountDownView(UIState()) {}
    }
}