package com.plcoding.coroutinesmasterclass

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.plcoding.coroutinesmasterclass.sections.coroutine_basics.fetchData
import com.plcoding.coroutinesmasterclass.sections.flow_fundanentals.LocationObserver
import com.plcoding.coroutinesmasterclass.sections.flows_in_practise.TimerUI
import com.plcoding.coroutinesmasterclass.ui.theme.CoroutinesMasterclassTheme
import com.plcoding.coroutinesmasterclass.util.CountdownScreen
import com.plcoding.coroutinesmasterclass.util.RotatingBoxScreen
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission
                    .ACCESS_COARSE_LOCATION, Manifest.permission
                    .ACCESS_FINE_LOCATION
            ), 0
        )

        val observer = LocationObserver(applicationContext)
        observer.observeLocation(1000L).onEach {
            println("Updated location: $it")

        }.launchIn(lifecycleScope)

        setContent {
            CoroutinesMasterclassTheme {
//                RotatingBoxScreen()
//                CountdownScreen()
                TimerUI()
            }
        }
    }
}