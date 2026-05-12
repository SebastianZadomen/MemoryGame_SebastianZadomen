package org.example.project.View.Screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
import org.example.project.ViewModel.CardBDViewModel
import org.example.project.ViewModel.SettingsViewModel
import org.example.project.ViewModel.UiUtils
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun Game(navigateBack: () -> Unit) {
    val cardVM: CardBDViewModel = viewModel { CardBDViewModel() }
    val setVM: SettingsViewModel = viewModel { SettingsViewModel() }
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

    // ESTADO DEL TEMPORIZADOR
    var timeElapsed by remember { mutableStateOf(0) }

    // Efecto para hacer funcionar el temporizador
    LaunchedEffect(gameCards.isNotEmpty(), isGameOver) {
        if (gameCards.isNotEmpty() && !isGameOver) {
            while (true) {
                delay(1000L) // Esperar 1 segundo
                timeElapsed++
            }
        }
    }

    // Preparar el juego al inicio
    LaunchedEffect(todasLasCards, columnas) {
        if (todasLasCards.isNotEmpty() && gameCards.isEmpty()) {
            cardVM.prepararEscenario(columnas)
            timeElapsed = 0 // Reiniciar tiempo
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
                    timeElapsed = 0 // Reiniciar tiempo al jugar de nuevo
                },
                onBackToMenu = navigateBack
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.95f)
                .background(uiVM.colorBgMenu, uiVM.hShape)
                .border(2.dp, uiVM.colorGold, uiVM.hShape)
                .padding(20.dp)
        ) {
            // CABECERA
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = navigateBack,
                    colors = ButtonDefaults.buttonColors(containerColor = uiVM.colorBgMenu),
                    shape = CutCornerShape(4.dp),
                    modifier = Modifier.border(1.dp, uiVM.colorGold, CutCornerShape(4.dp))
                ) {
                    Text("VOLVER", color = uiVM.colorGoldHover, fontFamily = spielgelFont, fontWeight = FontWeight.Bold)
                }

                // TIEMPO TRANSCURRIDO
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

            // TABLERO (Adaptativo con weight, sin LazyGrid)
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
                    matriz.isNotEmpty() -> {
                        // AQUÍ VOLVEMOS AL SISTEMA DE COLUMN/ROW CON WEIGHT(1f)
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            matriz.forEach { fila ->
                                Row(
                                    modifier = Modifier.weight(1f).fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    fila.forEach { carta ->
                                        Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                                            MemoryCard(
                                                card = carta,
                                                uiVM = uiVM,
                                                onClick = { cardVM.voltearCarta(carta) }
                                            )
                                        }
                                    }
                                    // Añadir espacios vacíos si la última fila está incompleta
                                    val espaciosFaltantes = columnas - fila.size
                                    for (i in 0 until espaciosFaltantes) {
                                        Spacer(modifier = Modifier.weight(1f).fillMaxHeight())
                                    }
                                }
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
            .fillMaxSize()
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
                modifier = Modifier.fillMaxSize()
            )
        } else {
            AsyncImage(
                model = card.Url,
                contentDescription = card.name,
                modifier = Modifier.fillMaxSize().graphicsLayer { rotationY = 180f }
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
    val minutos = tiempoFinal / 60
    val segundos = tiempoFinal % 60
    val tiempoFormateado = "${minutos.toString().padStart(2, '0')}:${segundos.toString().padStart(2, '0')}"

    Dialog(onDismissRequest = { }) {
        Card(
            modifier = Modifier.fillMaxWidth().border(2.dp, uiVM.colorGold, uiVM.hShape),
            shape = uiVM.hShape,
            colors = CardDefaults.cardColors(containerColor = uiVM.colorBgMenu)
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "¡VICTORIA!",
                    fontSize = 40.sp,
                    fontFamily = titleFont,
                    fontWeight = FontWeight.ExtraBold,
                    color = uiVM.colorGoldHover,
                    letterSpacing = 3.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(24.dp))

                Text(
                    text = "Has completado los $totalPares pares.",
                    fontSize = 18.sp,
                    color = uiVM.colorGold,
                    textAlign = TextAlign.Center,
                    fontFamily = spielgelFont
                )

                Spacer(Modifier.height(8.dp))

                // Mostrar tiempo en el Pop-up final
                Text(
                    text = "Tiempo: $tiempoFormateado",
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontFamily = spielgelFont
                )

                Spacer(Modifier.height(40.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    TextButton(
                        onClick = onBackToMenu,
                        modifier = Modifier.weight(1f).border(1.dp, uiVM.colorGold, CutCornerShape(4.dp)),
                        shape = CutCornerShape(4.dp)
                    ) {
                        Text("MENÚ", color = uiVM.colorGold, fontFamily = spielgelFont)
                    }

                    Button(
                        onClick = onPlayAgain,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = uiVM.colorHBlue),
                        shape = CutCornerShape(4.dp)
                    ) {
                        Text("REINTENTAR", color = Color.Black, fontWeight = FontWeight.Bold, fontFamily = spielgelFont)
                    }
                }
            }
        }
    }
}