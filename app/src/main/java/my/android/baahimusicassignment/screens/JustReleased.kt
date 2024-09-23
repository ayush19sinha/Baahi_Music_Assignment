package my.android.baahimusicassignment.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import my.android.baahimusicassignment.models.BAAHIBYXOMOYX
import my.android.baahimusicassignment.screens.screenComponents.CardItem
import my.android.baahimusicassignment.screens.screenComponents.NavigationIcon
import my.android.baahimusicassignment.screens.screenComponents.SearchBar
import my.android.baahimusicassignment.viewmodels.SearchViewModel

@Composable
fun JustReleasedRow(
    songs: List<BAAHIBYXOMOYX>,
    onSongClick: (BAAHIBYXOMOYX) -> Unit
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp)
    ) {
        items(songs) { song ->
            CardItem(
                cardImg = song.mp3_thumbnail_b,
                cardTitle = song.mp3_title
            ) {
                onSongClick(song)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllJustReleased(
    navController: NavController,
    songs: List<BAAHIBYXOMOYX>,
    searchViewModel: SearchViewModel = SearchViewModel()
) {
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
                        Text(
                            text = "Just Released",
                            modifier = Modifier.padding(start = 30.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
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
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(
                items = songs.filter {
                    searchQuery.isEmpty() || it.mp3_title.contains(searchQuery, ignoreCase = true)
                },
                key = { it.id }
            ) { song ->
                CardItem(
                    cardImg = song.mp3_thumbnail_b,
                    cardTitle = song.mp3_title,
                    onClick = {  }
                )
            }
        }
    }
}
