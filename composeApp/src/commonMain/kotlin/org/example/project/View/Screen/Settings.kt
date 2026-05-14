package org.example.project.View.Screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import memorygame_sebastianzadomen.composeapp.generated.resources.Res
import memorygame_sebastianzadomen.composeapp.generated.resources.spiegelsans
import org.example.project.Music.MusicManager
import org.example.project.SharedPreferences.SettingsRepository
import org.example.project.ViewModel.SettingsViewModel
import org.example.project.ViewModel.UiUtils
import org.jetbrains.compose.resources.Font

@Composable
fun Settings(navigateBack: () -> Unit) {
    val vm: SettingsViewModel = viewModel { SettingsViewModel(SettingsRepository()) }
    var showHelpDialog by remember { mutableStateOf(false) }
    val uiVM: UiUtils = viewModel { UiUtils() }
    val SpielgelFont = FontFamily(Font(Res.font.spiegelsans))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .background(uiVM.colorBgMenu, uiVM.hShape)
                    .border(2.dp, uiVM.colorGold, uiVM.hShape)
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "AJUSTES",
                    fontSize = 24.sp,
                    color = uiVM.colorGold,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = SpielgelFont,
                    letterSpacing = 2.sp
                )

                Spacer(Modifier.height(24.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, uiVM.colorGold, CutCornerShape(4.dp))
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "DIFICULTAD",
                            fontSize = 16.sp,
                            color = uiVM.colorGold,
                            fontFamily = SpielgelFont,
                            fontWeight = FontWeight.Bold
                        )

                        Box {
                            var expanded by remember { mutableStateOf(false) }
                            Row(
                                modifier = Modifier
                                    .width(180.dp)
                                    .height(40.dp)
                                    .background(uiVM.colorDarkPanel, CutCornerShape(50))
                                    .border(1.dp, uiVM.colorGold, CutCornerShape(50))
                                    .clickable { expanded = true }
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = vm.itemsDificultad[vm.selectedDificultad].uppercase(),
                                    color = uiVM.colorGoldHover,
                                    fontSize = 14.sp,
                                    fontFamily = SpielgelFont,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Desplegar",
                                    tint = uiVM.colorGold
                                )
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier
                                    .background(uiVM.colorDarkPanel)
                                    .border(1.dp, uiVM.colorGold)
                            ) {
                                vm.itemsDificultad.forEachIndexed { index, title ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = title.uppercase(),
                                                color = uiVM.colorGoldHover,
                                                fontFamily = SpielgelFont
                                            )
                                        },
                                        onClick = { vm.changeDificultad(index); expanded = false }
                                    )
                                }
                            }
                        }
                    }

                    Divider(color = Color(0xFF1E282D), thickness = 1.dp, modifier = Modifier.fillMaxWidth(0.8f))

                    Row(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "MÚSICA",
                            fontSize = 16.sp,
                            color = uiVM.colorGold,
                            fontFamily = SpielgelFont,
                            fontWeight = FontWeight.Bold
                        )

                        Switch(
                            checked = vm.switchSettings,
                            onCheckedChange = { isChecked ->
                                vm.switchSettings = isChecked
                                MusicManager.toggleMusic(isChecked)
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = uiVM.colorHBlue
                            )
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))


                Spacer(Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, uiVM.colorGold, CutCornerShape(4.dp))
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { showHelpDialog = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F1923)),
                        border = BorderStroke(1.dp, uiVM.colorHBlue),
                        shape = CutCornerShape(4.dp)
                    ) {
                        Text(
                            text = "VER REGLAS DEL JUEGO",
                            color = uiVM.colorHBlue,
                            fontWeight = FontWeight.Bold,
                            fontFamily = SpielgelFont
                        )
                    }

                    Button(
                        onClick = { vm.restoreSettings() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A1010)),
                        border = BorderStroke(1.dp, Color.Red),
                        shape = CutCornerShape(4.dp)
                    ) {
                        Text(
                            text = "RESTAURAR VALORES",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontFamily = SpielgelFont
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .size(48.dp)
                    .background(uiVM.colorBgMenu, CircleShape)
                    .border(2.dp, uiVM.colorGold, CircleShape)
                    .clickable { navigateBack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Cerrar",
                    tint = uiVM.colorGold,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }

    if (showHelpDialog) {
        Dialog(onDismissRequest = { showHelpDialog = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .border(2.dp, uiVM.colorGold, uiVM.hShape),
                shape = uiVM.hShape,
                colors = CardDefaults.cardColors(containerColor = uiVM.colorBgMenu)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "CÓMO JUGAR",
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = uiVM.colorGoldHover,
                        letterSpacing = 2.sp,
                        fontFamily = SpielgelFont,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(24.dp))

                    Text(
                        text = "1. Toca una carta.\n" +
                                "2. Memorízala.\n" +
                                "3. Encuentra su pareja.\n" +
                                "4. Completa sin fallar.",
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 16.sp,
                        color = uiVM.colorGold,
                        lineHeight = 26.sp,
                        fontFamily = SpielgelFont,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(32.dp))

                    Button(
                        onClick = { showHelpDialog = false },
                        modifier = Modifier.width(160.dp), // Damos un ancho fijo al botón para que se vea más como en la imagen
                        colors = ButtonDefaults.buttonColors(containerColor = uiVM.colorHBlue),
                        shape = CutCornerShape(4.dp)
                    ) {
                        Text(
                            "ENTENDIDO",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontFamily = SpielgelFont
                        )
                    }
                }
            }
        }
    }
}