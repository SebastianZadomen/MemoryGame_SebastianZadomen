package org.example.project.View.Screen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.ViewModel.SettingsViewModel

@Composable
fun Settings(navigateBack: () -> Unit) {
    val vm: SettingsViewModel = viewModel { SettingsViewModel() }
    var showHelpDialog by remember { mutableStateOf(false) }

    val colorBgMenu = Color(0xFF091428)
    val colorGold = Color(0xFFC8AA6E)
    val colorGoldHover = Color(0xFFF0E6D2)
    val colorHextechBlue = Color(0xFF0AC8B9)
    val colorDarkPanel = Color(0xFF010A13)
    val colorRedMuted = Color(0xFF7A1D1A)
    val hextechShape = CutCornerShape(topStart = 8.dp, bottomEnd = 8.dp)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000)),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .background(colorBgMenu, hextechShape)
                .border(2.dp, colorGold, hextechShape)
                .padding(24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(48.dp))

                    Text(
                        text = "AJUSTES DE PARTIDA",
                        fontSize = 24.sp,
                        color = colorGoldHover,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )

                    TextButton(
                        onClick = navigateBack,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Text("X", color = colorGold, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(Modifier.height(24.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorDarkPanel, hextechShape)
                        .border(1.dp, Color(0xFF1E282D), hextechShape)
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Dificultad", fontSize = 18.sp, color = colorGold)

                        Box {
                            var expanded by remember { mutableStateOf(false) }
                            val buttonTitle = vm.itemsDificultad[vm.selectedDificultad]

                            Button(
                                onClick = { expanded = true },
                                colors = ButtonDefaults.buttonColors(containerColor = colorBgMenu),
                                shape = CutCornerShape(4.dp),
                                modifier = Modifier.border(1.dp, colorGold, CutCornerShape(4.dp))
                            ) {
                                Text(text = buttonTitle, color = colorGoldHover)
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier.background(colorDarkPanel).border(1.dp, colorGold)
                            ) {
                                vm.itemsDificultad.forEachIndexed { index, title ->
                                    DropdownMenuItem(
                                        text = { Text(title, color = colorGoldHover) },
                                        onClick = {
                                            vm.selectedDificultad = index
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Música del Cliente", fontSize = 18.sp, color = colorGold)

                        Switch(
                            checked = vm.switchSettings,
                            onCheckedChange = { vm.switchSettings = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = colorHextechBlue,
                                uncheckedThumbColor = Color(0xFF5C5B57),
                                uncheckedTrackColor = Color(0xFF1E2328)
                            )
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorDarkPanel, hextechShape)
                        .border(1.dp, Color(0xFF1E282D), hextechShape)
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Tutorial", fontSize = 18.sp, color = colorGold)

                        Button(
                            onClick = { showHelpDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = colorBgMenu),
                            shape = CutCornerShape(4.dp),
                            modifier = Modifier.border(1.dp, colorHextechBlue, CutCornerShape(4.dp))
                        ) {
                            Text("VER REGLAS", color = colorHextechBlue, fontWeight = FontWeight.Bold)
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Restaurar Valores", fontSize = 18.sp, color = colorGold)

                        Button(
                            onClick = { vm.restoreSettings() },
                            colors = ButtonDefaults.buttonColors(containerColor = colorRedMuted),
                            shape = CutCornerShape(4.dp)
                        ) {
                            Text("RESTAURAR", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        if (showHelpDialog) {
            Dialog(onDismissRequest = { showHelpDialog = false }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, colorGold, hextechShape),
                    shape = hextechShape,
                    colors = CardDefaults.cardColors(containerColor = colorBgMenu)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "CÓMO JUGAR",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorGoldHover,
                            letterSpacing = 2.sp
                        )

                        Spacer(Modifier.height(24.dp))

                        Text(
                            text = "1. Toca una carta .\n\n" +
                                    "2. Memorizala.\n\n" +
                                    "3. Encuentra su pareja.\n\n" +
                                    "4. Completa sin fallar.",
                            fontSize = 16.sp,
                            color = colorGold,
                            lineHeight = 22.sp
                        )

                        Spacer(Modifier.height(32.dp))

                        Button(
                            onClick = { showHelpDialog = false },
                            colors = ButtonDefaults.buttonColors(containerColor = colorHextechBlue),
                            shape = CutCornerShape(4.dp)
                        ) {
                            Text("Entendido", color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

