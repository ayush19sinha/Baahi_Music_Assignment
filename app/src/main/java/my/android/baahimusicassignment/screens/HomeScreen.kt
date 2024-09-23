package my.android.baahimusicassignment.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import my.android.baahimusicassignment.navigation.Routes
import my.android.baahimusicassignment.viewmodels.MusicViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(musicViewModel: MusicViewModel, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Home",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            Section(
                title = "Trending Songs",
                onViewAllClick = { navController.navigate(Routes.TRENDING_SONGS) }
            ) {
                TrendingSongsRow(
                    songs = musicViewModel.trendingSongs.collectAsState().value,
                    onSongClick = {  }
                )
            }

            Section(
                title = "Just Released",
                onViewAllClick = { navController.navigate(Routes.JUST_RELEASED) }
            ) {
                JustReleasedRow(
                    songs = musicViewModel.justReleased.collectAsState().value,
                    onSongClick = { }
                )
            }

            Section(
                title = "Recommended Artists",
                onViewAllClick = { navController.navigate(Routes.RECOMMENDED_ARTISTS) }
            ) {
                RecommendedArtistsRow(
                    artists = musicViewModel.recommendedArtists.collectAsState().value,
                    onArtistClick = { }
                )
            }
        }
    }
}

@Composable
fun Section(
    title: String,
    onViewAllClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        SectionTitle(title = title, onClick = onViewAllClick)
        Spacer(modifier = Modifier.height(8.dp))
        content()
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun SectionTitle(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            text = "View all",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    val musicViewModel = MusicViewModel()
    val navController = rememberNavController()
    HomeScreen(musicViewModel = musicViewModel, navController = navController)
}
