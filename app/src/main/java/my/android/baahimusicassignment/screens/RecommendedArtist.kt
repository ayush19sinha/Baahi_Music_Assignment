package my.android.baahimusicassignment.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import my.android.baahimusicassignment.models.BAAHIBYXOMOYXX
import my.android.baahimusicassignment.screens.screenComponents.CardItem
import my.android.baahimusicassignment.screens.screenComponents.NavigationIcon
import my.android.baahimusicassignment.screens.screenComponents.SearchBar
import my.android.baahimusicassignment.viewmodels.SearchViewModel

@Composable
fun RecommendedArtistsRow(
    artists: List<BAAHIBYXOMOYXX>,
    onArtistClick: (BAAHIBYXOMOYXX) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(artists) { artist ->
            CardItem(
                cardImg = artist.artist_image,
                cardTitle = artist.artist_name
            ) {
                onArtistClick(artist)
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllRecommendedArtist(navController: NavController,
                         artists: List<BAAHIBYXOMOYXX>,
                         searchViewModel: SearchViewModel = SearchViewModel()) {
    val searchBarExpanded by searchViewModel.searchBarExpanded.collectAsState()
    val searchQuery by searchViewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (searchBarExpanded) {
                        SearchBar(
                            query = searchQuery,
                            onQueryChange = searchViewModel::updateSearchQuery,
                            onSearchToggle = searchViewModel::toggleSearchBar
                        )
                    } else {
                        Text("Recommended Artists", modifier = Modifier.padding(start = 30.dp))
                    }
                },
                actions = {
                    IconButton(onClick = searchViewModel::toggleSearchBar) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    NavigationIcon(onNavigateUp = navController::navigateUp)
                }
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = innerPadding.calculateBottomPadding() + 8.dp,
                start = 16.dp,
                end = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(
                items = artists.filter {
                    searchQuery.isEmpty() || it.artist_name.contains(searchQuery, ignoreCase = true)
                },
                key = { it.id }
            ) { artist ->
                CardItem(
                    cardImg = artist.artist_image,
                    cardTitle = artist.artist_name,
                    onClick = {  }
                )
            }
        }
    }
}