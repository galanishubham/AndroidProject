package com.example.astronautsapp.ui.screens.astronauts

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.astronautsapp.AstronautApplication
import com.example.astronautsapp.data.AstronautsRepository
import com.example.astronautsapp.domain.model.Astronaut
import com.facebook.AccessToken
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


/**
 * UI state for the Home screen
 */
sealed interface AstronautsListUiState {
    data class Success(val astronauts: List<Astronaut>) : AstronautsListUiState
    object Error : AstronautsListUiState
    object Loading : AstronautsListUiState
    object SignInRequired: AstronautsListUiState
}

class AstronautsViewModel(private val repository: AstronautsRepository): ViewModel() {

    var astronautsListUiState: AstronautsListUiState by mutableStateOf(AstronautsListUiState.Loading)
        private set

    init {
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired

        if(isLoggedIn) {
            getAstronauts()
        } else {
            astronautsListUiState = AstronautsListUiState.SignInRequired
        }
    }

   fun getAstronauts() {
        viewModelScope.launch {

            astronautsListUiState = AstronautsListUiState.Loading

            astronautsListUiState = try {
                val result = repository.getAstronauts()
                AstronautsListUiState.Success(result)
            } catch (e: IOException) {
                Log.d("Error", e.toString())

                AstronautsListUiState.Error
            } catch (e: HttpException) {
                AstronautsListUiState.Error
            }
        }
   }

    /**
     * Factory for [AstronautsViewModel] that takes [AstronautsRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {

                val application = (this[APPLICATION_KEY] as AstronautApplication)
                val astronautRepository = application.container.astronautsRepository
                AstronautsViewModel(repository = astronautRepository)
            }
        }
    }
}
