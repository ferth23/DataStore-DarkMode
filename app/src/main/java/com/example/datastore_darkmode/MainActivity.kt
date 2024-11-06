package com.example.datastore_darkmode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.botonesapp.components.BotonIcono
import com.example.botonesapp.components.BotonNormal
import com.example.botonesapp.components.BotonNormal2
import com.example.botonesapp.components.BotonOutline
import com.example.botonesapp.components.BotonSwitch
import com.example.botonesapp.components.BotonTexto
import com.example.botonesapp.components.DarkMode
import com.example.botonesapp.components.FloatingAction
import com.example.botonesapp.components.Space
import com.example.datastore_darkmode.ui.theme.DataStore_DarkModeTheme
import kotlinx.coroutines.CoroutineScope

class MainActivity : ComponentActivity() {
    private lateinit var store: StoreDarkMode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store = StoreDarkMode ( this )

        enableEdgeToEdge()
        setContent {
            val scope = rememberCoroutineScope()
            val darkMode = remember { mutableStateOf ( false ) }

            LaunchedEffect ( Unit ) {
                store.isDarkMode.collect { isDark -> darkMode.value = isDark }
            }

            DataStore_DarkModeTheme ( darkTheme = darkMode.value ) {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content ( darkMode = darkMode, store = store, scope = scope )
                }
            }
        }
    }
}

@Composable
fun Content ( darkMode: MutableState<Boolean>, store: StoreDarkMode, scope: CoroutineScope ) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BotonNormal ()
        Space ()
        BotonNormal2 ()
        Space ()
        BotonTexto ()
        Space ()
        BotonOutline ()
        Space ()
        BotonIcono ()
        Space ()
        BotonSwitch ()
        Space ()
        DarkMode (darkMode = darkMode, store = store, scope = scope )
        Space ()
        FloatingAction ()
    }
}