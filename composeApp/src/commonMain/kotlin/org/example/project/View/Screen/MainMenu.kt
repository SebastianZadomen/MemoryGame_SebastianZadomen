package org.example.project.View.Screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.text.font.FontWeight
import memorygame_sebastianzadomen.composeapp.generated.resources.Res
import memorygame_sebastianzadomen.composeapp.generated.resources.mysticgrace
import memorygame_sebastianzadomen.composeapp.generated.resources.contador
import org.example.project.ViewModel.Screen1ViewModel

import org.jetbrains.compose.resources.Font

@Composable
fun MainMenu(navigateTo2: () -> Unit, navigateTo3: () -> Unit, navigateTo4: () -> Unit) {
    val vm: Screen1ViewModel = viewModel { Screen1ViewModel() }
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

                Button(onClick = navigateTo3) { Text("Score", fontFamily = ContadorFont, fontSize = 15.sp) }
            }
        Spacer(Modifier.height(15.dp))
            AnimatedVisibility(vm.showMessage) {
                Button(onClick = navigateTo4) { Text("Ajuste", fontFamily = ContadorFont, fontSize = 15.sp) }

            }
        }
    }


