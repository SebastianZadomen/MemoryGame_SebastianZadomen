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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import memorygame_sebastianzadomen.composeapp.generated.resources.Res
import memorygame_sebastianzadomen.composeapp.generated.resources.spielgelsans
import org.example.project.ViewModel.SettingsViewModel
import org.example.project.ViewModel.UiUtils
import org.jetbrains.compose.resources.Font

@Composable
fun Settings(navigateBack: () -> Unit) {
    val vm: SettingsViewModel = viewModel { SettingsViewModel() }
    var showHelpDialog by remember { mutableStateOf(false) }
    val uiVM : UiUtils = viewModel { UiUtils() }
    val SpielgelFont = FontFamily(
        Font(Res.font.spielgelsans))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .background(uiVM.colorBgMenu, uiVM.hShape)
                .border(2.dp, uiVM.colorGold, uiVM.hShape)
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
                        text = "AJUSTES",
                        fontSize = 24.sp,
                        color = uiVM.colorGoldHover,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        fontFamily = SpielgelFont
                    )

                    TextButton(
                        onClick = navigateBack,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Text("X", color = uiVM.colorGold, fontSize = 24.sp, fontWeight = FontWeight.Bold,fontFamily = SpielgelFont)
                    }
                }

                Spacer(Modifier.height(24.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(uiVM.colorDarkPanel, uiVM.hShape)
                        .border(1.dp, Color(0xFF1E282D), uiVM.hShape)
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Dificultad", fontSize = 18.sp, color = uiVM.colorGold, fontFamily = SpielgelFont)

                        Box {
                            var expanded by remember { mutableStateOf(false) }
                            val buttonTitle = vm.itemsDificultad[vm.selectedDificultad]

                            Button(
                                onClick = { expanded = true },
                                colors = ButtonDefaults.buttonColors(containerColor = uiVM.colorBgMenu),
                                shape = CutCornerShape(4.dp),
                                modifier = Modifier.border(1.dp, uiVM.colorGold, CutCornerShape(4.dp))
                            ) {
                                Text(text = buttonTitle, color = uiVM.colorGoldHover, fontFamily = SpielgelFont)
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier.background(uiVM.colorDarkPanel).border(1.dp, uiVM.colorGold)
                            ) {
                                vm.itemsDificultad.forEachIndexed { index, title ->
                                    DropdownMenuItem(
                                        text = { Text(title, color = uiVM.colorGoldHover, fontFamily = SpielgelFont) },
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
                        Text("Música", fontSize = 18.sp, color = uiVM.colorGold, fontFamily = SpielgelFont)

                        Switch(
                            checked = vm.switchSettings,
                            onCheckedChange = { vm.switchSettings = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = uiVM.colorHBlue,
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
                        .background(uiVM.colorDarkPanel, uiVM.hShape)
                        .border(1.dp, Color(0xFF1E282D), uiVM.hShape)
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Tutorial", fontSize = 18.sp, color = uiVM.colorGold, fontFamily = SpielgelFont)

                        Button(
                            onClick = { showHelpDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = uiVM.colorBgMenu),
                            shape = CutCornerShape(4.dp),
                            modifier = Modifier.border(1.dp, uiVM.colorHBlue, CutCornerShape(4.dp))
                        ) {
                            Text("VER REGLAS", color = uiVM.colorHBlue, fontWeight = FontWeight.Bold, fontFamily = SpielgelFont)
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Restaurar Valores", fontSize = 18.sp, color = uiVM.colorGold, fontFamily = SpielgelFont)

                        Button(
                            onClick = { vm.restoreSettings() },
                            colors = ButtonDefaults.buttonColors(containerColor = uiVM.colorRedMuted),
                            shape = CutCornerShape(4.dp)
                        ) {
                            Text("RESTAURAR", color = Color.White, fontWeight = FontWeight.Bold, fontFamily = SpielgelFont)
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
                        .border(2.dp, uiVM.colorGold, uiVM.hShape),
                    shape = uiVM.hShape,
                    colors = CardDefaults.cardColors(containerColor = uiVM.colorBgMenu)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "CÓMO JUGAR",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = uiVM.colorGoldHover,
                            letterSpacing = 2.sp,
                            fontFamily = SpielgelFont
                        )

                        Spacer(Modifier.height(24.dp))

                        Text(
                            text = "1. Toca una carta .\n" +
                                    "2. Memorizala.\n" +
                                    "3. Encuentra su pareja.\n" +
                                    "4. Completa sin fallar.",
                            fontSize = 16.sp,
                            color = uiVM.colorGold,
                            lineHeight = 22.sp,
                            fontFamily = SpielgelFont
                        )

                        Spacer(Modifier.height(32.dp))

                        Button(
                            onClick = { showHelpDialog = false },
                            colors = ButtonDefaults.buttonColors(containerColor = uiVM.colorHBlue),
                            shape = CutCornerShape(4.dp)
                        ) {
                            Text("Entendido", color = Color.Black, fontWeight = FontWeight.Bold, fontFamily = SpielgelFont)
                        }
                    }
                }
            }
        }
    }
}

