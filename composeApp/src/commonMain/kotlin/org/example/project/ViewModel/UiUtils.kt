package org.example.project.ViewModel

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import memorygame_sebastianzadomen.composeapp.generated.resources.Res
import memorygame_sebastianzadomen.composeapp.generated.resources.mysticgrace
import org.jetbrains.compose.resources.Font

class UiUtils : ViewModel() {

    val colorBgMenu = Color(0xFF091428)
    val colorGold = Color(0xFFC8AA6E)
    val goldDark = Color(0xFFEAA634)

    val colorGoldStrog = Color(0xFFAF8000)

    val colorGoldHover = Color(0xFFF0E6D2)
    val colorHBlue = Color(0xFF0AC8B9)
    val colorDarkPanel = Color(0xFF010A13)
    val colorRedMuted = Color(0xFF7A1D1A)
    val hShape = CutCornerShape(topStart = 8.dp, bottomEnd = 8.dp)



}