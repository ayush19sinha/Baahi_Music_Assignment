package my.android.baahimusicassignment

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import my.android.baahimusicassignment.navigation.Navigation
import my.android.baahimusicassignment.ui.theme.BaahiMusicAssignmentTheme
import my.android.baahimusicassignment.viewmodels.MusicViewModel
import my.android.baahimusicassignment.viewmodels.SplashViewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val iconView = splashScreenView.iconView
            val scaleAndRotate = AnimatorSet().apply {
                playTogether(
                    ObjectAnimator.ofFloat(iconView, View.SCALE_X, 1f, 1.5f),
                    ObjectAnimator.ofFloat(iconView, View.SCALE_Y, 1f, 1.5f),
                    ObjectAnimator.ofFloat(iconView, View.ROTATION, 0f, 360f)
                )
                duration = 1000L
                interpolator = OvershootInterpolator()
            }
            val slideUp = ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.TRANSLATION_Y,
                0f,
                -splashScreenView.view.height.toFloat()
            ).apply {
                interpolator = AnticipateInterpolator()
                duration = 1000L
            }
            AnimatorSet().apply {
                playSequentially(
                    scaleAndRotate,
                    slideUp
                )
                doOnEnd { splashScreenView.remove() }
                start()
            }
        }

        setContent {

            val splashViewModel = SplashViewModel()
            val isLoading by splashViewModel.isLoading.collectAsState()
            splashScreen.setKeepOnScreenCondition { isLoading }
            val musicViewModel: MusicViewModel = viewModel()
            val navController = rememberNavController()
            val isSystemDarkTheme = isSystemInDarkTheme()

            BaahiMusicAssignmentTheme(darkTheme = isSystemDarkTheme, dynamicColor = false) {
                Navigation(navController = navController, musicViewModel = musicViewModel)
            }
        }
    }
}