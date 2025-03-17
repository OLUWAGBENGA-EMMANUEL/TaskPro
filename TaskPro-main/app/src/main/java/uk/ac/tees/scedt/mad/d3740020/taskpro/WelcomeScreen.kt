package uk.ac.tees.scedt.mad.d3740020.taskpro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to My Todo App!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp),
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Get organized and stay productive with your tasks.",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 32.dp),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )

            ElevatedButton(
                onClick = {
                    navController.navigate("todo")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                contentPadding = PaddingValues(16.dp)

            ) {
                Text(
                    text = "Get Started",
                    fontSize = 18.sp
                )
            }
        }
    }
}