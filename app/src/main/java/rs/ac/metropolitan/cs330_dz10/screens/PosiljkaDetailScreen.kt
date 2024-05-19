package rs.ac.metropolitan.cs330_dz10.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import rs.ac.metropolitan.cs330_dz10.Posiljke
import rs.ac.metropolitan.cs330_dz10.R
@Composable
fun PosiljkaDetailScreen(vm: AppViewModel, id: String, paddingValues: PaddingValues) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues), contentAlignment = Alignment.TopCenter) {
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            item{
                PosiljkaBasicData(posiljka = vm.getPosiljka(id),
                    seeFrom = {vm.switchSeeFrom()},
                    goBack = {vm.goBack()}
                )
            }
            item{
                AnimatedVisibility (vm.seeFrom.value) {
                    PosiljkaFrom(vm.getPosiljka(id))
                }
            }
        }
    }
}

@Composable
fun PosiljkaBasicData(posiljka: Posiljke?, seeFrom: ()-> Unit, goBack: ()-> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        IconButton(
            modifier = Modifier.background(Color.Transparent),
            onClick = {goBack()}) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        posiljka?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(text = "Name: ${posiljka.name}",
                    style = MaterialTheme.typography.titleLarge)
                Text(
                    text = "Email: ${posiljka.email}",
                    color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Country to: ${posiljka.countryTo}",
                    color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "City to: ${posiljka.cityTo}",
                    color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Street to: ${posiljka.streetTo}",
                    color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Weight: ${posiljka.weight}",
                    color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Lomljivo: ${posiljka.lomljivo}",
                    color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )

                Button(onClick = { seeFrom() },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "From")
                }
            }

        }
    }
}

@Composable
fun PosiljkaFrom(posiljka: Posiljke?){
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(16.dp),
    ) {
        posiljka?.let {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Country from: ${posiljka.countryFrom}",
                    color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "City from: ${posiljka.cityFrom}",
                    color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Street from: ${posiljka.streetFrom}",
                    color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                if (posiljka.lomljivo){
                    Image(
                        painter = painterResource(id = R.drawable.broken),
                        contentDescription = "Lomljivo",
                    )
                }

            }
        }
    }
}