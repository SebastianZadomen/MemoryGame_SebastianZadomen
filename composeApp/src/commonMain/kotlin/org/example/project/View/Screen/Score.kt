package org.example.project.View.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import memorygame_sebastianzadomen.composeapp.generated.resources.Res
import memorygame_sebastianzadomen.composeapp.generated.resources.spiegelsans
import org.example.project.ViewModel.ScoreBDViewModel
import org.example.project.ViewModel.SettingsViewModel
import org.example.project.ViewModel.UiUtils
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.FontResource

@Composable
fun Score(userId: String, navigateBack: () -> Unit) {
    val uiVM: UiUtils = viewModel { UiUtils() }
    val scoreVM: ScoreBDViewModel = viewModel { ScoreBDViewModel() }
    val setVM: SettingsViewModel = viewModel { SettingsViewModel() }

    val allScores by scoreVM.allScore.collectAsState()
    val spielgelFont = FontFamily(Font(Res.font.spiegelsans))

    val dificultadActual = setVM.itemsDificultad[setVM.selectedDificultad]
    val scoresFiltrados = allScores
        .filter { it.dificultad == dificultadActual }
        .sortedBy { it.time }

    val miMejorRecord = scoresFiltrados.minByOrNull { it.time }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("RANKING: ${dificultadActual.uppercase()}", fontSize = 24.sp, color = uiVM.colorGold, fontWeight = FontWeight.Bold, fontFamily = spielgelFont)
            Spacer(Modifier.height(10.dp))

            Box(
                modifier = Modifier.height(380.dp).width(600.dp)
                    .border(2.dp, uiVM.colorGold, uiVM.hShape)
                    .background(uiVM.colorBgMenu, uiVM.hShape)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    item {
                        Row(Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                            Text("#", Modifier.width(30.dp), color = uiVM.colorGold, fontWeight = FontWeight.Bold)
                            Text("Jugador", Modifier.weight(1f), color = uiVM.colorGold, fontWeight = FontWeight.Bold)
                            Text("Tiempo", Modifier.width(80.dp), color = uiVM.colorGold, fontWeight = FontWeight.Bold)
                        }
                    }

                    itemsIndexed(scoresFiltrados) { index, score ->
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("${index + 1}", Modifier.width(30.dp), color = uiVM.colorGoldHover, fontSize = 14.sp)
                            Text(score.name, Modifier.weight(1f), color = Color.White, fontSize = 16.sp)
                            Text("${score.time}s", Modifier.width(80.dp), color = Color.White)
                        }
                        Spacer(Modifier.height(1.dp).fillMaxWidth().background(Color.DarkGray))
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Box(
                modifier = Modifier.width(600.dp)
                    .background(uiVM.colorDarkPanel, uiVM.hShape)
                    .border(1.dp, uiVM.colorHBlue, uiVM.hShape)
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(androidx.compose.material.icons.Icons.Default.Star, contentDescription = null, tint = uiVM.colorHBlue, modifier = Modifier.size(30.dp))
                    Spacer(Modifier.width(10.dp))
                    Column {
                        Text("TU MEJOR MARCA EN $dificultadActual", fontSize = 10.sp, color = uiVM.colorHBlue)
                        Text(
                            text = if (miMejorRecord != null) "${miMejorRecord.time} segundos" else "Sin récords",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Box(contentAlignment = Alignment.BottomEnd, modifier = Modifier.fillMaxWidth().width(600.dp)) {
                IconButton(onClick = navigateBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = uiVM.colorGoldHover)
                }
            }
        }
    }
}

