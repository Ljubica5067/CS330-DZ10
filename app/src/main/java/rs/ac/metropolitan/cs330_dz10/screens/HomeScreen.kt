package rs.ac.metropolitan.cs330_dz10.screens

import androidx.compose.runtime.Composable
import android.Manifest
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import rs.ac.metropolitan.cs330_dz10.Posiljke
@Composable
fun HomeScreen(vm: AppViewModel, paddingValues: PaddingValues) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        vm.granted.value = isGranted
    }

    Column {
        if (!vm.granted.value) {
            InternetPermission(launcher)
        } else {
            ListPosiljke(vm = vm, paddingValues = paddingValues)
        }
    }
}

@Composable
private fun InternetPermission(launcher: ManagedActivityResultLauncher<String, Boolean>) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Internet permission not granted",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(8.dp)
            )
            Button(onClick = { launcher.launch(Manifest.permission.INTERNET) }) {
                Text("Request permission")
            }
        }
    }
}

@Composable
fun ListPosiljke(vm: AppViewModel = viewModel(), paddingValues: PaddingValues) {
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        items(vm.posiljke) { posiljka ->
            PrikaziPosiljku(posiljka) {
                vm.navigateToPosiljkaDetails(it)
            }
        }

    }
}

@Composable
fun PrikaziPosiljku(posiljka: Posiljke, onSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .width(300.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Name: ${posiljka.name} " +
                            "\nEmail: ${posiljka.email}" +
                            "\nCountry to: ${posiljka.countryTo}" +
                            "\nCity to: ${posiljka.cityTo}" +
                            "\nStreet to: ${posiljka.streetTo}" +
                            "\nWeight: ${posiljka.weight}",
                    modifier = Modifier
                        .animateContentSize(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioLowBouncy,
                                stiffness = Spring.StiffnessLow
                            )
                        )
                        .clickable { expanded = !expanded },
                    maxLines = if (!expanded) 2 else 10
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                Icons.Default.ArrowForward,
                contentDescription = "Forward",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable(onClick = { onSelected(posiljka.id) })
            )
        }
    }
}