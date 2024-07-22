package com.example.kmmappfirst.android.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherDisplay(modifier: Modifier,weatherViewModel: WeatherViewModel= koinViewModel()) {

    val state = weatherViewModel.state.collectAsState().value
    val currentDate = remember { LocalDate.now() }
    val dateFormatter = remember { DateTimeFormatter.ofPattern("MMM dd, yyyy") }
    val formattedDate = remember { currentDate.format(dateFormatter) }
    val weekday =  currentDate.dayOfWeek.getDisplayName(java.time.format.TextStyle.SHORT, Locale.ENGLISH)


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        LaunchedEffect(weatherViewModel) {
            // Trigger the fetchMovies() when the composable is first launched.
            weatherViewModel.processIntent(WeatherActions.LoadWeather)
        }
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.error.isNotBlank() -> {
                Text(
                    text = state.error,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            else -> {
                WeatherScreen(state = state ,formattedDate ,weekday    )
            }
        }

    }


}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WeatherScreen(state: WeatherViewState , formattedDate: String , weekday: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        //verticalArrangement = Arrangement.Center,
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {


            Column {
                Text(text = "Lahore", style = MaterialTheme.typography.titleMedium)
                Text(text = formattedDate, style = MaterialTheme.typography.bodySmall)
            }
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0xFFFAEDED)),// .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center

            )
            {
                Icon(Icons.Filled.LocationOn, contentDescription = "Location", tint = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Today's Report",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )

        //Spacer(modifier = Modifier.height(10.dp))

        //image of weather

        Text(
            text = "Cloudy",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = state.weather?.main?.temp.toString(),
            style = TextStyle(fontSize = 80.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = "°C",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))



    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherScreen(
        WeatherViewState(
            isLoading = false,
            weather = null,
            error = "",

            ),
        formattedDate = "Aug 12, 2021",
        weekday = "Wed"
    )
}


// Data class to hold the weather information

