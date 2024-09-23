package my.android.baahimusicassignment.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import my.android.baahimusicassignment.api.RetrofitInstance
import my.android.baahimusicassignment.models.BAAHIBYXOMOY
import my.android.baahimusicassignment.models.BAAHIBYXOMOYX
import my.android.baahimusicassignment.models.BAAHIBYXOMOYXX

class MusicViewModel : ViewModel() {
    val trendingSongs = MutableStateFlow<List<BAAHIBYXOMOY>>(emptyList())
    val justReleased = MutableStateFlow<List<BAAHIBYXOMOYX>>(emptyList())
    val recommendedArtists = MutableStateFlow<List<BAAHIBYXOMOYXX>>(emptyList())

    init {
        viewModelScope.launch {
            try {
                trendingSongs.value = RetrofitInstance.api.getTrendingSongs()["BAAHI_BY_XOMOY"] ?: emptyList()
                justReleased.value = RetrofitInstance.api.getJustReleased()["BAAHI_BY_XOMOY"] ?: emptyList()
                recommendedArtists.value = RetrofitInstance.api.getRecommendedArtists()["BAAHI_BY_XOMOY"] ?: emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}