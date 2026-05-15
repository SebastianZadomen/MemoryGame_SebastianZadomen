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
    import androidx.compose.foundation.shape.CutCornerShape
    import androidx.compose.material3.Button
    import androidx.compose.material3.ButtonDefaults
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.LaunchedEffect
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.graphics.StrokeJoin
    import androidx.compose.ui.graphics.drawscope.Stroke
    import androidx.compose.ui.platform.testTag
    import androidx.compose.ui.text.TextStyle
    import androidx.compose.ui.text.font.FontFamily
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.lifecycle.viewmodel.compose.viewModel
    import memorygame_sebastianzadomen.composeapp.generated.resources.Res
    import memorygame_sebastianzadomen.composeapp.generated.resources.beaufort
    import memorygame_sebastianzadomen.composeapp.generated.resources.spiegelsans


    import org.example.project.ViewModel.Screen1ViewModel
    import org.example.project.ViewModel.UiUtils
    import org.jetbrains.compose.resources.Font
    import org.example.project.Music.MusicManager
    import org.example.project.SharedPreferences.SettingsRepository
    import org.example.project.ViewModel.SettingsViewModel
    import org.jetbrains.compose.resources.FontResource

    @Composable
    fun MainMenu(navigateTo2: () -> Unit, navigateTo3: () -> Unit, navigateTo4: () -> Unit, ) {
        val vm: Screen1ViewModel = viewModel { Screen1ViewModel() }
        val uiVM: UiUtils = viewModel { UiUtils() }
        val setVM: SettingsViewModel = viewModel { SettingsViewModel(SettingsRepository()) }

        val SpielgelFont = FontFamily(Font(Res.font.spiegelsans))
        val TitleFont = FontFamily(Font(Res.font.beaufort))

        LaunchedEffect(Unit) {
            if (setVM.switchSettings) {
                MusicManager.toggleMusic(true)
            }
        }

        val sizeMemory = if (vm.showMessage) 48.sp else 64.sp
        val sizeGame = if (vm.showMessage) 56.sp else 84.sp

        val buttonModifier = Modifier
            .fillMaxWidth(0.7f)
            .height(56.dp)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.90f)
                    .fillMaxHeight(0.90f)
                    .background(uiVM.colorBgMenu, uiVM.hShape)
                    .border(2.dp, uiVM.colorGold, uiVM.hShape)
                    .padding(24.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { vm.modifyShowMessage() }
                            .testTag("titulo_principal"),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy((-15).dp)
                        ) {
                            val memoryFontSize = if (vm.showMessage) 80.sp else 90.sp
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "Memory",
                                    fontSize = memoryFontSize,
                                    fontFamily = TitleFont,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = uiVM.colorGoldStrog,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(drawStyle = Stroke(width = 10f, join = StrokeJoin.Round))
                                )
                                Text(
                                    text = "Memory",
                                    fontSize = memoryFontSize,
                                    fontFamily = TitleFont,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = uiVM.colorGold,
                                    textAlign = TextAlign.Center
                                )
                            }

                            val gameFontSize = if (vm.showMessage) 100.sp else 130.sp
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "Game",
                                    fontSize = gameFontSize,
                                    fontFamily = TitleFont,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = uiVM.colorGoldStrog,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(drawStyle = Stroke(width = 14f, join = StrokeJoin.Round))
                                )
                                Text(
                                    text = "Game",
                                    fontSize = gameFontSize,
                                    fontFamily = TitleFont,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = uiVM.colorGold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(40.dp))

                    AnimatedVisibility(vm.showMessage) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Button(
                                onClick = navigateTo2,
                                modifier = buttonModifier.testTag("boton_jugar"),
                                colors = ButtonDefaults.buttonColors(containerColor = uiVM.colorGold),
                                shape = CutCornerShape(4.dp)
                            ) {
                                Text("JUGAR", fontFamily = SpielgelFont, color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            }

                            Button(
                                onClick = navigateTo3,
                                modifier = buttonModifier,
                                colors = ButtonDefaults.buttonColors(containerColor = uiVM.colorBgMenu),
                                border = borderStrokeDefault(uiVM),
                                shape = CutCornerShape(4.dp)
                            ) {
                                Text("SCORE", fontFamily = SpielgelFont, color = uiVM.colorGold, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            }

                            Button(
                                onClick = navigateTo4,
                                modifier = buttonModifier,
                                colors = ButtonDefaults.buttonColors(containerColor = uiVM.colorBgMenu),
                                border = borderStrokeDefault(uiVM),
                                shape = CutCornerShape(4.dp)
                            ) {
                                Text("AJUSTES", fontFamily = SpielgelFont, color = uiVM.colorGold, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun borderStrokeDefault(uiVM: UiUtils) = androidx.compose.foundation.BorderStroke(1.dp, uiVM.colorGold)