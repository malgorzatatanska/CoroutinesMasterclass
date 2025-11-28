package com.plcoding.coroutinesmasterclass.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.coroutinesmasterclass.util.view.CountdownViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CountdownScreen(
    viewModel: CountdownViewModel = viewModel(), modifier: Modifier =
        Modifier
) {

    val value by viewModel.count.collectAsStateWithLifecycle()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text("Odliczanie ${value}")
            Button(
                onClick = {
                    viewModel.start()
                },
                enabled = true,
                modifier = Modifier.padding(20.dp)
            ) {
                Text("Start counting")
            }
        }
    }
}

@Preview(
    showBackground = true, device = Devices.PIXEL_4, showSystemUi = true,
    widthDp = 411,   // szerokość Pixel 4 (portrait)
    heightDp = 891   // wysokość Pixel 4 (portrait)

)
@Composable
fun CountdownScreenPreview() {
    CountdownScreen(viewModel = CountdownViewModel())
}



