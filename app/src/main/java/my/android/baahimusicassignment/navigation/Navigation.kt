package my.android.baahimusicassignment.navigation


import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import my.android.baahimusicassignment.screens.AllJustReleased
import my.android.baahimusicassignment.screens.AllRecommendedArtist
import my.android.baahimusicassignment.screens.AllTrendingSongs
import my.android.baahimusicassignment.screens.HomeScreen
import my.android.baahimusicassignment.viewmodels.MusicViewModel

@Composable
fun Navigation(navController: NavHostController, musicViewModel: MusicViewModel) {

    NavHost(navController = navController,
        startDestination = Routes.HOME,
        enterTransition ={ slideInHorizontally { it }   },
        exitTransition = { slideOutHorizontally { -it }  },
        popEnterTransition = { slideInHorizontally { -it } },
        popExitTransition = { slideOutHorizontally { it } }

    ) {

        composable(Routes.HOME){
            HomeScreen(musicViewModel = musicViewModel, navController = navController)
        }

        composable(Routes.JUST_RELEASED){
            AllJustReleased(navController = navController,
                songs = musicViewModel.justReleased.collectAsState().value)
        }

        composable(Routes.TRENDING_SONGS){
            AllTrendingSongs(navController = navController,
                songs = musicViewModel.trendingSongs.collectAsState().value)
        }

        composable(Routes.RECOMMENDED_ARTISTS){
            AllRecommendedArtist(navController = navController,
                artists = musicViewModel.recommendedArtists.collectAsState().value)

        }
    }
}