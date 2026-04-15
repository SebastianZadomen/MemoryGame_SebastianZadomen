package org.example.project.View.Screen

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.ViewModel.Pantalla1ViewModel

@Composable
fun Screen1(navigateTo2: () -> Unit, navigateTo3: () -> Unit) {
    val vm: Pantalla1ViewModel = viewModel { Pantalla1ViewModel() }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { vm.modifyShowMessage() }) {
            Text("Show message!")
        }
        AnimatedVisibility(vm.showMessage) {
            Text("Hello World!", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }
    }
}
