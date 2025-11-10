package com.example.userapplication.presentation.user_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.userapplication.R
import com.example.userapplication.domain.model.UserDetails
import com.example.userapplication.presentation.user_details.UserDetailsViewModel

@Composable
fun UserDetailsScreen(
    userDetailsViewModel: UserDetailsViewModel = hiltViewModel()
) {
    val userDetailsScreenState = userDetailsViewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        userDetailsScreenState.user?.let {
            ProfileCard(user = userDetailsScreenState.user)
        }

        if (userDetailsScreenState.error.isNotBlank()) {
            Text(
                text = userDetailsScreenState.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 20.dp)
                    .align(alignment = Alignment.Center)
            )
        }
        if (userDetailsScreenState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun ProfileCard(user: UserDetails) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = user.photo,
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground), // Display while loading
                    error = painterResource(id = R.drawable.ic_launcher_background)        // Display if an error occurs
                ),
                contentDescription = user.name,
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp)
                    .then(Modifier),
                contentScale = ContentScale.Fit
            )

            Text(
                text = user.name,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )

            Text(
                text = user.company,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            InfoItem(label = "Username: ", value = user.name)
            InfoItem(label = "Email: ", value = user.email)
            InfoItem(label = "Phone: ", value = user.phone)
            InfoItem(label = "Address: ", value = "${user.address}, ${user.state}, ${user.country}")
            InfoItem(label = "Zip Code: ", value = user.zip)
        }
    }
}

@Composable
fun InfoItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        Text(text = value, fontSize = 14.sp, color = Color.DarkGray)
    }
}
