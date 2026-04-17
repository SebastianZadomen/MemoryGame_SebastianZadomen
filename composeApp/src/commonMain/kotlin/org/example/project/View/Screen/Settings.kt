package org.example.project.View.Screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.View.Navigation.Route
import org.example.project.ViewModel.Pantalla1ViewModel
import org.example.project.ViewModel.SettingsViewModel

@Composable
fun Settings(navigateBack: () -> Unit) {
    val vm: SettingsViewModel = viewModel { SettingsViewModel() }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Ajustes", fontSize = 30.sp)

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .fillMaxHeight(0.8f)
                    .border(2.dp, Color.Black, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.TopCenter

            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box {
                        var expanded by remember { mutableStateOf(false) }
                        val buttonTitle = vm.itemsDificultad[vm.selectedDificultad]

                        Button(onClick = { expanded = true }) {
                            Text(text = "Dificultad: $buttonTitle" , fontSize = 25.sp)
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            vm.itemsDificultad.forEachIndexed { index, title ->
                                DropdownMenuItem(
                                    text = { Text(title) },
                                    onClick = {
                                        vm.selectedDificultad = index
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                    Spacer(Modifier.width(16.dp))

                    Box()
                    {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Music", fontSize = 20.sp)

                            Spacer(Modifier.width(16.dp))

                            Switch(
                                checked = vm.switchSettings,
                                onCheckedChange = { nuevoEstado ->
                                    vm.switchSettings = nuevoEstado
                                },
                                colors = SwitchDefaults.colors(
                                    uncheckedThumbColor = Color.Red,
                                    checkedThumbColor = Color.Green
                                )
                            )
                        }
                    }
                }
            }


            Box(contentAlignment = Alignment.BottomEnd, modifier = Modifier.fillMaxWidth())
            {
                IconButton(onClick = navigateBack)
                {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                }
            }
        }
    }

}