package org.example.project.View.Screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.text.font.FontWeight
import memorygame_sebastianzadomen.composeapp.generated.resources.Res
import memorygame_sebastianzadomen.composeapp.generated.resources.beaufort
import org.example.project.ViewModel.Screen1ViewModel
import memorygame_sebastianzadomen.composeapp.generated.resources.spielgelsans
import memorygame_sebastianzadomen.composeapp.generated.resources.ghore
import org.example.project.ViewModel.UiUtils
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.FontResource

import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MainMenu(navigateTo2: () -> Unit, navigateTo3: () -> Unit, navigateTo4: () -> Unit) {
    val vm: Screen1ViewModel = viewModel { Screen1ViewModel() }
    val uiVM : UiUtils = viewModel { UiUtils() }


    val SpielgelFont = FontFamily(
        Font(Res.font.spielgelsans)
    )
    val TitleFont = FontFamily(
        Font(Res.font.ghore)
    )

    val sizeFontTitle = if (vm.showMessage) 90.sp else 120.sp
    val lineHeighttTitle = if (vm.showMessage) 80.sp else 110.sp
    val textoTitulo = if (vm.showMessage) "Memory Game" else "Memory\nGame"
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    )
    {
        Box(
            modifier = Modifier.fillMaxWidth(0.90f).fillMaxHeight(0.90f).background(uiVM.colorBgMenu, uiVM.hShape)
                .border(2.dp, uiVM.colorGold, uiVM.hShape)
                .padding(24.dp)
        )
        {
            Column(
                modifier = Modifier.fillMaxSize()/*.paint(painterResource(id= Res.drawable.))*/,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Box {
                    Text(
                        text = textoTitulo,
                        textAlign = TextAlign.Center,
                        fontSize = sizeFontTitle,
                        fontFamily = TitleFont,
                        lineHeight = lineHeighttTitle,
                        fontWeight = FontWeight.ExtraBold,
                        color = uiVM.colorGoldStrog,
                        letterSpacing = 0.04.sp,
                        style = TextStyle(
                            drawStyle = Stroke(
                                width = 8f,
                                join = StrokeJoin.Round
                            )
                        )
                    )
                    Text(
                        textoTitulo,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium,
                        lineHeight = lineHeighttTitle,
                        fontFamily = TitleFont,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = sizeFontTitle,
                        color = uiVM.colorGold,
                        letterSpacing = 0.04.sp,
                        modifier = Modifier.clickable(onClick = { vm.modifyShowMessage() })

                    )
                }
                Spacer(Modifier.height(30.dp))

                AnimatedVisibility(vm.showMessage) {
                    Button(onClick = navigateTo2) {
                        Text(
                            "Jugar",
                            fontFamily = SpielgelFont,
                            color = uiVM.colorGold,
                            fontSize = 15.sp
                        )
                    }
                }
                Spacer(Modifier.height(15.dp))
                AnimatedVisibility(vm.showMessage) {

                    Button(onClick = navigateTo3) {
                        Text(
                            "Score",
                            fontFamily = SpielgelFont,
                            color = uiVM.colorGold,
                            fontSize = 15.sp
                        )
                    }
                }
                Spacer(Modifier.height(15.dp))
                AnimatedVisibility(vm.showMessage) {
                    Button(onClick = navigateTo4) {
                        Text(
                            "Ajuste",
                            fontFamily = SpielgelFont,
                            color = uiVM.colorGold,
                            fontSize = 15.sp
                        )
                    }

                }
            }
        }
    }
}


