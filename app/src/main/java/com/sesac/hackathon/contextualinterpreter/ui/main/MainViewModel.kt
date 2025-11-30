package com.sesac.hackathon.contextualinterpreter.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sesac.hackathon.contextualinterpreter.data.model.ConversationItem
import com.sesac.hackathon.contextualinterpreter.data.model.SoundEvent
import com.sesac.hackathon.contextualinterpreter.data.repository.ConversationRepository
import com.sesac.hackathon.contextualinterpreter.data.repository.SoundRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class MainUiState(
    val soundEvents: List<SoundEvent> = emptyList(),
    val conversationItems: List<ConversationItem> = emptyList(),
    val latestSoundEvent: SoundEvent? = null
)

class MainViewModel : ViewModel() {
    private val soundRepository = SoundRepository()
    private val conversationRepository = ConversationRepository()

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            soundRepository.getSoundEvents().collectLatest { event ->
                _uiState.value = _uiState.value.copy(
                    soundEvents = (_uiState.value.soundEvents + event).takeLast(5), // Keep last 5 events
                    latestSoundEvent = event
                )
            }
        }

        viewModelScope.launch {
            conversationRepository.getConversationStream().collectLatest { item ->
                _uiState.value = _uiState.value.copy(
                    conversationItems = (_uiState.value.conversationItems + item).takeLast(20) // Keep last 20 items
                )
            }
        }
    }
}
