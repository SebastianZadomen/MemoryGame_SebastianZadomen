package org.example.project.View.Screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import memorygame_sebastianzadomen.composeapp.generated.resources.Res
import memorygame_sebastianzadomen.composeapp.generated.resources.backimage
import memorygame_sebastianzadomen.composeapp.generated.resources.beaufort
import memorygame_sebastianzadomen.composeapp.generated.resources.spiegelsans
import org.example.project.Card.Card
import org.example.project.SharedPreferences.SettingsRepository
import org.example.project.ViewModel.CardBDViewModel
import org.example.project.ViewModel.ScoreBDViewModel
import org.example.project.ViewModel.SettingsViewModel
import org.example.project.ViewModel.UiUtils
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun Game(navigateBack: () -> Unit) {
    val cardVM: CardBDViewModel = viewModel { CardBDViewModel() }
    val setVM: SettingsViewModel = viewModel { SettingsViewModel(SettingsRepository()) }
    val uiVM: UiUtils = viewModel { UiUtils() }

    val gameCards by cardVM.gameCards.collectAsState()
    val todasLasCards by cardVM.allCards.collectAsState()
    val isLoading by cardVM.isLoading.collectAsState()
    val errorMessage by cardVM.errorMessage.collectAsState()

    val spielgelFont = FontFamily(Font(Res.font.spiegelsans))
    val titleFont = FontFamily(Font(Res.font.beaufort))

    val columnas = setVM.gridDificultad.coerceAtLeast(2)

    val paresEncontrados = gameCards.count { it.isMatched } / 2
    val totalPares = gameCards.size / 2
    val isGameOver = gameCards.isNotEmpty() && gameCards.all { it.isMatched }

    var timeElapsed by remember { mutableStateOf(0) }

    LaunchedEffect(gameCards.isNotEmpty(), isGameOver) {
        if (gameCards.isNotEmpty() && !isGameOver) {
            while (true) {
                delay(1000L)
                timeElapsed++
            }
        }
    }

    LaunchedEffect(todasLasCards.isNotEmpty(), columnas) {
        if (todasLasCards.isNotEmpty()) {
            cardVM.prepararEscenario(columnas)
            timeElapsed = 0
        }
    }
    val matriz = remember(gameCards, columnas) {
        gameCards.chunked(columnas)
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        if (isGameOver) {
            VictoryDialog(
                titleFont = titleFont,
                spielgelFont = spielgelFont,
                uiVM = uiVM,
                totalPares = totalPares,
                tiempoFinal = timeElapsed,
                onPlayAgain = {
                    cardVM.prepararEscenario(columnas)
                    timeElapsed = 0
                },
                onBackToMenu = {
                    cardVM.prepararEscenario(columnas)
                    timeElapsed = 0
                    navigateBack()
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(uiVM.colorBgMenu, uiVM.hShape)
                .padding(8.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 16.dp, start = 8.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        cardVM.prepararEscenario(columnas)
                        timeElapsed = 0
                        navigateBack()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = uiVM.colorBgMenu),
                    shape = CutCornerShape(4.dp),
                    modifier = Modifier.border(1.dp, uiVM.colorGold, CutCornerShape(4.dp))
                ) {
                    Text("VOLVER", color = uiVM.colorGoldHover, fontFamily = spielgelFont, fontWeight = FontWeight.Bold)
                }

                val minutos = timeElapsed / 60
                val segundos = timeElapsed % 60
                val tiempoFormateado = "${minutos.toString().padStart(2, '0')}:${segundos.toString().padStart(2, '0')}"

                Text(
                    text = tiempoFormateado,
                    fontSize = 24.sp,
                    color = Color.White,
                    fontFamily = spielgelFont,
                    fontWeight = FontWeight.ExtraBold
                )

                Card(
                    colors = CardDefaults.cardColors(containerColor = uiVM.colorDarkPanel),
                    border = BorderStroke(1.dp, Color(0xFF1E282D)),
                    shape = CutCornerShape(4.dp)
                ) {
                    Text(
                        text = "PARES: $paresEncontrados / $totalPares",
                        style = MaterialTheme.typography.titleMedium,
                        color = uiVM.colorGold,
                        fontFamily = spielgelFont,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }

            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                when {
                    isLoading -> {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(color = uiVM.colorGold)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Invocando cartas...", color = uiVM.colorGold, fontFamily = spielgelFont)
                        }
                    }
                    errorMessage != null -> {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = uiVM.colorRedMuted),
                            modifier = Modifier.padding(16.dp).border(1.dp, Color.Red, RoundedCornerShape(8.dp))
                        ) {
                            Text(
                                text = "Error de Conexión:\n\n$errorMessage",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontFamily = spielgelFont,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                    gameCards.isNotEmpty() -> {

                        val (columnasVisuales, paddingExterior, espacioEntreCartas) = when (gameCards.size) {
                            6 -> Triple(3, 2.dp, 16.dp)
                            8 -> Triple(3, 4.dp, 12.dp)
                            else -> Triple(3, 8.dp, 8.dp)
                        }

                            LazyVerticalGrid(
                                columns = GridCells.Fixed(columnasVisuales),
                                contentPadding = PaddingValues(paddingExterior),
                                verticalArrangement = Arrangement.spacedBy(espacioEntreCartas, Alignment.CenterVertically),
                                horizontalArrangement = Arrangement.spacedBy(espacioEntreCartas),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(gameCards) { carta ->
                                    MemoryCard(
                                        card = carta,
                                        uiVM = uiVM,
                                        onClick = { cardVM.voltearCarta(carta) }
                                    )

                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun MemoryCard(
    card: Card,
    uiVM: UiUtils,
    onClick: () -> Unit
) {
    val rotation by animateFloatAsState(
        targetValue = if (card.isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 400)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.66f)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            }
            .clip(CutCornerShape(4.dp))
            .border(1.dp, uiVM.colorGoldStrog, CutCornerShape(4.dp))
            .clickable(enabled = !card.isFlipped && !card.isMatched) { onClick() }
    ) {
        if (rotation <= 90f) {
            Image(
                painter = painterResource(Res.drawable.backimage),
                contentDescription = "Reverso",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            AsyncImage(
                model = card.Url,
                contentDescription = card.name,
                modifier = Modifier.fillMaxSize().graphicsLayer { rotationY = 180f },
                contentScale = ContentScale.Crop
            )
            if (card.isMatched) {
                Box(modifier = Modifier.fillMaxSize().background(uiVM.colorGold.copy(alpha = 0.2f)))
            }
        }
    }
}
@Composable
fun VictoryDialog(
    titleFont: FontFamily,
    spielgelFont: FontFamily,
    uiVM: UiUtils,
    totalPares: Int,
    tiempoFinal: Int,
    onPlayAgain: () -> Unit,
    onBackToMenu: () -> Unit
) {
    val scoreVM: ScoreBDViewModel = viewModel { ScoreBDViewModel() }
    val setVM: SettingsViewModel = viewModel {
        SettingsViewModel(SettingsRepository())
    }
    var nombreInput by remember { mutableStateOf(setVM.nombreUsuarioGuardado) }
    var registrado by remember { mutableStateOf(false) }

    val tiempoFormateado = "${(tiempoFinal / 60).toString().padStart(2, '0')}:${(tiempoFinal % 60).toString().padStart(2, '0')}"

    Dialog(onDismissRequest = { }) {
        Card(
            modifier = Modifier.fillMaxWidth().border(2.dp, uiVM.colorGold, uiVM.hShape),
            shape = uiVM.hShape,
            colors = CardDefaults.cardColors(containerColor = uiVM.colorBgMenu)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("¡VICTORIA!", fontSize = 36.sp, fontFamily = titleFont, color = uiVM.colorGoldHover)
                Text("Tiempo total: $tiempoFormateado", color = Color.White, fontFamily = spielgelFont)

                Spacer(Modifier.height(20.dp))

                if (!registrado && setVM.nombreUsuarioGuardado.isEmpty()) {
                    OutlinedTextField(
                        value = nombreInput,
                        onValueChange = { if (it.length <= 12) nombreInput = it },
                        label = { Text("Introduce tu nombre:", color = uiVM.colorGold) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )

                    Button(
                        onClick = {
                            if (nombreInput.isNotBlank()) {
                                setVM.nombreUsuarioGuardado =
                                    nombreInput


                                scoreVM.guardarOActualizarScore(
                                    nombre = nombreInput,
                                    tiempo = tiempoFinal,
                                    dificultad = setVM.itemsDificultad[setVM.selectedDificultad]
                                )
                                registrado = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = uiVM.colorGold)
                    ) {
                        Text("REGISTRAR RÉCORD", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                }
                else if (!registrado && setVM.nombreUsuarioGuardado.isNotEmpty()) {
                    Text("Jugador: ${setVM.nombreUsuarioGuardado}", color = uiVM.colorGold)
                    Button(
                        onClick = {
                            scoreVM.guardarOActualizarScore(
                                nombre = setVM.nombreUsuarioGuardado,
                                tiempo = tiempoFinal,
                                dificultad = setVM.itemsDificultad[setVM.selectedDificultad]
                            )
                            registrado = true
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = uiVM.colorGold)
                    ) {
                        Text("SUBIR PUNTUACIÓN AUTOMÁTICAMENTE", color = Color.Black)
                    }
                } else {
                    Text("¡RÉCORD REGISTRADO!", color = uiVM.colorHBlue, fontWeight = FontWeight.Bold)
                }

                Spacer(Modifier.height(30.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    TextButton(onClick = onBackToMenu, modifier = Modifier.weight(1f).border(1.dp, uiVM.colorGold, CutCornerShape(4.dp))) {
                        Text("MENÚ", color = uiVM.colorGold)
                    }
                    Button(onClick = onPlayAgain, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = uiVM.colorHBlue)) {
                        Text("REINTENTAR", color = Color.Black)
                    }
                }
            }
        }
    }
}
