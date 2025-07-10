package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                    LemonadeApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    TreeWithTextAndImage(modifier = Modifier.fillMaxSize())

}

@Composable
fun TreeWithTextAndImage(modifier: Modifier = Modifier) {

    var state by remember { mutableStateOf(1) }
    var clicksCount by remember { mutableStateOf(0) }
    var randomSqueeze by remember { mutableStateOf(0) }
    randomSqueeze = (2..6).random()

    val imageResource = when (state) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val textResource = when (state) {
        1 -> R.string.tap_the_lemon
        2 -> R.string.keep_tapping
        3 -> R.string.drink_lemonade
        else -> R.string.empty_glass
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = stringResource(R.string.lemon_tree_content_description),
            modifier = Modifier.clickable {
                if (state == 2 && clicksCount < randomSqueeze) {
                    clicksCount++
                } else if (state == 2 && clicksCount == randomSqueeze) {
                    state = (state + 1) % 4
                    clicksCount = 0
                } else { state = (state + 1) % 4 }
            }
        )

        Spacer(modifier = Modifier.padding(16.dp))
        
        Text(text = stringResource(textResource))


    }
}
