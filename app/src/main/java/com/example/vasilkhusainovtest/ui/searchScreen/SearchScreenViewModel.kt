package com.example.vasilkhusainovtest.ui.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vasilkhusainovtest.common.ApiConfig.DEBOUNCE_DELAY
import com.example.vasilkhusainovtest.common.AppConfig.EMPTY_ERROR_MESSAGE
import com.example.vasilkhusainovtest.common.AppConfig.MIN_CHARS_SEARCH
import com.example.vasilkhusainovtest.domain.SearchInfoGitHubRepository
import com.example.vasilkhusainovtest.ui.searchScreen.Effect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.Duration.Companion.milliseconds


class SearchScreenViewModel(private val searchInfoGitHubRepository: SearchInfoGitHubRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    private val _effect = MutableSharedFlow<Effect>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    private var jobSearch: Job? = null

    fun eventOpenSearchPanel() {
        _state.update {
            it.copy(
                panelSearchSelector = PanelSearchSelector.Open,
            )
        }
    }

    fun eventNavigateToWebView(url: String?) {
        url?.let {
            viewModelScope.launch(Dispatchers.Main) {
                _effect.emit(Effect.NavigateToWebView(url))

            }
        }
    }

    fun eventNavigateToRepository(owner: String?, repositoryName: String?) {
        if (owner != null && repositoryName != null) {

            viewModelScope.launch(Dispatchers.Main) {

                _effect.emit(
                    Effect.NavigateToTreeRepository(
                        owner = owner,
                        repositoryName = repositoryName
                    )
                )
            }
        } else {
            viewModelScope.launch(Dispatchers.Main) {
                _effect.emit(Effect.ShowSnackbar(""))
            }
        }
    }

    fun eventUpdatePage() {
        jobSearch?.cancel()
        if (state.value.searchText.length >= MIN_CHARS_SEARCH) {
            jobSearch = viewModelScope.launch(Dispatchers.IO) {
                searchElement(state.value.searchText)
            }
        }
    }

    fun eventChangeText(textValue: String) {

        jobSearch?.cancel()

        if (textValue.length >= MIN_CHARS_SEARCH) {
            jobSearch = viewModelScope.launch(Dispatchers.IO) {
                searchElement(textValue)
            }
        }
        _state.update {
            it.copy(
                searchText = textValue,
            )
        }
    }

    fun eventExpandDescription(id: Int) {

        _state.update { currentState ->
            val updatedList = currentState.items.map { item ->
                if (item.id == id) {
                    item.withExpanded(!item.isExpanded)
                } else {
                    item
                }
            }
            currentState.copy(items = updatedList)
        }
    }

    private suspend fun searchElement(text: String) {
        try {
            delay(DEBOUNCE_DELAY.milliseconds)
            _state.update {
                it.copy(
                    contentStatus = ContentStatus.Loading
                )
            }

            val itemsSearch = searchInfoGitHubRepository.getSearchItems(text)
            if (itemsSearch.isEmpty()) {
                _state.update {
                    it.copy(
                        contentStatus = ContentStatus.EmptyContent
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        items = itemsSearch,
                        contentStatus = ContentStatus.Success
                    )
                }
            }
        } catch (e: Exception) {
            if (e !is CancellationException) {
                _state.update {
                    it.copy(
                        contentStatus = ContentStatus.Error
                    )
                }
                viewModelScope.launch(Dispatchers.Main) {
                    _effect.emit(Effect.ShowSnackbar(e.message ?: EMPTY_ERROR_MESSAGE))
                }
            }
        }
    }
}