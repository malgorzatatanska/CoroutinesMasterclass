package com.plcoding.coroutinesmasterclass.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.coroutinesmasterclass.util.view.CountdownViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CountdownScreen(
    viewModel: CountdownViewModel = viewModel(), modifier: Modifier = Modifier
) {

    val value by viewModel.count.collectAsStateWithLifecycle()
    val enteredValue = viewModel.input

    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("Wpisz wybrana warrtosc")
            TextField(
                onValueChange = { newValue: String ->
                    val number = newValue.toIntOrNull() ?: 0
                    viewModel.setCounter(number)

                },
                value = enteredValue.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 20.dp,
                        bottom = 20.dp
                    )
            )
            Text("Odliczanie ${value}")
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment =
                    Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        viewModel.start()
                    }, enabled = true, modifier = Modifier.padding(20.dp)
                ) {
                    Text("Start counting")
                }
                Button(
                    onClick = { viewModel.reset() },
                    modifier = Modifier.padding(
                        20.dp
                    )
                ) {
                    Text("Wyzeruj")
                }
            }


        }

    }
}

@Preview(
    showBackground = true, device = Devices.PIXEL_4, showSystemUi = true,


    )
@Composable
fun CountdownScreenPreview() {
    CountdownScreen()
}



