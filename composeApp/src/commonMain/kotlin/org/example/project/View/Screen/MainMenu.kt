package org.example.project.View.Screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.ViewModel.Pantalla1ViewModel
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import memorygame_sebastianzadomen.composeapp.generated.resources.Res
import memorygame_sebastianzadomen.composeapp.generated.resources.mysticgrace
import memorygame_sebastianzadomen.composeapp.generated.resources.contador

import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainMenu(navigateTo2: () -> Unit, navigateTo3: () -> Unit) {
    val vm: Pantalla1ViewModel = viewModel { Pantalla1ViewModel() }
    val MysticGrace = FontFamily(
        Font(Res.font.mysticgrace))
    val ContadorFont = FontFamily(
        Font(Res.font.contador))

    val sizeFontTitle = if (vm.showMessage) 50.sp else 60.sp
    val textoTitulo = if (vm.showMessage) "Memory Game" else "Memory\nGame"
    Column(modifier = Modifier.fillMaxSize()/*.paint(painterResource(id= Res.drawable.))*/, horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
            Text(
                textoTitulo,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                lineHeight = 60.sp,
                fontFamily = MysticGrace,
                fontWeight = FontWeight.ExtraBold,
                fontSize = sizeFontTitle,
                modifier = Modifier.clickable(onClick = { vm.modifyShowMessage() })
            )
        Spacer(Modifier.height(30.dp))

        AnimatedVisibility(vm.showMessage) {
            Button(onClick = navigateTo2) { Text("Jugar", fontFamily = ContadorFont , fontSize = 15.sp) }
            }
        Spacer(Modifier.height(15.dp))
            AnimatedVisibility(vm.showMessage) {

                Button(onClick = navigateTo2) { Text("Score", fontFamily = ContadorFont, fontSize = 15.sp) }
            }
        Spacer(Modifier.height(15.dp))
            AnimatedVisibility(vm.showMessage) {
                Button(onClick = navigateTo3) { Text("Ajuste", fontFamily = ContadorFont, fontSize = 15.sp) }

            }
        }
    }


