package org.example.project.View.Screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.filter
import org.example.project.Card.Card
import org.example.project.ViewModel.CardBDViewModel
import org.example.project.ViewModel.SettingsViewModel
import org.example.project.ViewModel.UiUtils


@Composable
fun Game(navigateBack: () -> Unit) {
    val uiVM : UiUtils = viewModel { UiUtils() }
    val cardVM : CardBDViewModel = viewModel { CardBDViewModel() }
    val setVM : SettingsViewModel = viewModel { SettingsViewModel() }
    /*
    val filteredList = cardVM.cards.filter { result ->
        result.name
            .lowercase()
            .contains(query.lowercase().trim())
    }
*/
    LazyVerticalGrid(
        columns = GridCells.Fixed(setVM.gridDificultad),
        contentPadding = PaddingValues(8.dp)
    ) {
      //  items(cardVM.cards) { character ->
           /* Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { viewModel.selectedCharacter = character
                        navController.navigate(Destinations.DetailScreen.route) },
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = vDesign.colorCard
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = character?.img,
                        contentDescription = "Imatge character",
                        modifier = Modifier
                            .size(vDesign.imageSizeScreen1)
                            .clip(RoundedCornerShape(16.dp))

                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = character.name,
                            style = MaterialTheme.typography.titleLarge,
                            color = vDesign.colorFont,
                            maxLines = 2
                        )
                        Text(
                            text = "Age: ${character.age}",
                            color = vDesign.colorFont,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
*/
            }
    /*
    val rotation by animateFloatAsState(
        targetValue = if (card.isFlipped || card.isMatched) 180f else 0f,
        animationSpec = tween(durationMillis = 500)
    )*/


    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Pantalla 2", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))
    }
    Button(onClick = navigateBack) { Text("Volver") }

}
