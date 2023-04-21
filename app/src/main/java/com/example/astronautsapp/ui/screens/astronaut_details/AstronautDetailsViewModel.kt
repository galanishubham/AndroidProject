package com.example.astronautsapp.ui.screens.astronaut_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.astronautsapp.AstronautApplication
import com.example.astronautsapp.data.AstronautsRepository
import com.example.astronautsapp.domain.model.AstronautDetails
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


/**
 * UI state for the Astronaut details screen
 */
sealed interface AstronautDetailsUiState {
    data class Success(val astronautDetails: AstronautDetails) : AstronautDetailsUiState
    object Error : AstronautDetailsUiState
    object Loading : AstronautDetailsUiState
}

class AstronautDetailsViewModel(private val repository: AstronautsRepository, private val stateHandle: SavedStateHandle? = null): ViewModel() {
    var astronautDetailsUiState: AstronautDetailsUiState by mutableStateOf(AstronautDetailsUiState.Loading)
        private set

    init {
        stateHandle?.get<Int>("astronautId")?.let { id: Int -> getAstronautDetails(id) }
    }

    fun getAstronautDetails(id: Int) {
        viewModelScope.launch {
            astronautDetailsUiState = AstronautDetailsUiState.Loading

            astronautDetailsUiState = try {

            val result = repository.getAstronautDetails(id)
            AstronautDetailsUiState.Success(result)
            } catch (e: IOException) {
                AstronautDetailsUiState.Error
            } catch (e: HttpException) {
                AstronautDetailsUiState.Error
            }
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY] as AstronautApplication)

                val savedStateHandle = extras.createSavedStateHandle()

                return AstronautDetailsViewModel(
                    application.container.astronautsRepository,
                    savedStateHandle
                ) as T
            }
        }
    }
}