package com.example.projektcaly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    var kwotaInput by remember { mutableStateOf("") }
    var opisInput by remember { mutableStateOf("") }
    val wydatki = remember { mutableStateListOf<Wydatek>() }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = kwotaInput,
                onValueChange = { kwotaInput = it },
                label = { Text("Kwota") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = opisInput,
                onValueChange = { opisInput = it },
                label = { Text("Opis") },
                modifier = Modifier.weight(2f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val kwota = kwotaInput.toDoubleOrNull() ?: 0.0
            wydatki.add(Wydatek(kwota, opisInput))
            kwotaInput = ""
            opisInput = ""
        }) {
            Text("Dodaj wydatek")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(wydatki) { wydatek ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${wydatek.kwota} zł - ${wydatek.opis}")
                    Button(onClick = { wydatki.remove(wydatek) }) {
                        Text("Usuń")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Suma wydatków: ${wydatki.sumOf { it.kwota }} zł")
    }
}

data class Wydatek(val kwota: Double, val opis: String)
