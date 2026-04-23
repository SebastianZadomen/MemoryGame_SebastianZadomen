package org.example.project.View.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import memorygame_sebastianzadomen.composeapp.generated.resources.Res
import memorygame_sebastianzadomen.composeapp.generated.resources.spielgelsans
import org.example.project.ViewModel.UiUtils
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.FontResource


@Composable
fun Score(userId: String, navigateBack: () -> Unit) {
    val uiVM : UiUtils = viewModel { UiUtils() }
    val SpielgelFont = FontFamily(
        Font(Res.font.spielgelsans))
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text("SCORE", fontSize = 30.sp, color = uiVM.colorGold, fontWeight = FontWeight.Bold, fontFamily = SpielgelFont)
            Spacer(Modifier.height(10.dp))

            Box(
                modifier = Modifier.height(450.dp).width(600.dp).border(2.dp, uiVM.colorGold, uiVM.hShape).background(uiVM.colorBgMenu, uiVM.hShape)
            )
            {
                LazyColumn {

                }

            }

            Box(contentAlignment = Alignment.BottomEnd, modifier = Modifier.fillMaxWidth())
            {
                IconButton(onClick = navigateBack )
                {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Volver" , tint = uiVM.colorGoldHover)
                }
            }
        }
    }
}



