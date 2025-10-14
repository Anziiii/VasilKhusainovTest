package com.example.vasilkhusainovtest.ui.treeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vasilkhusainovtest.domain.SearchInfoGitHubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class TreeScreenViewModel(
    private val searchInfoGitHubRepository: SearchInfoGitHubRepository,
    private val ownerName: String,
    private val repositoryName: String,
    private val path: String,
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    private val _effect = MutableSharedFlow<Effect>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    titleHeader = if (path.isEmpty()) repositoryName else {
                        path
                    },
                    contentStatus = ContentStatus.Loading
                )
            }

            try {
                val response =
                    searchInfoGitHubRepository.getTreeRepository(ownerName, repositoryName, path)

                _state.update {
                    it.copy(
                        items = response,
                        contentStatus = ContentStatus.Success
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        contentStatus = ContentStatus.Error
                    )
                }
            }
        }
    }

    fun eventUpdatePage() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    searchInfoGitHubRepository.getTreeRepository(ownerName, repositoryName, path)

                _state.update {
                    it.copy(
                        items = response
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        contentStatus = ContentStatus.Error
                    )
                }
            }
        }
    }

    fun eventNavigateToBack() {

        viewModelScope.launch(Dispatchers.Main) {
            _effect.emit(Effect.NavigateBack)
        }
    }

    fun eventNavigateToFile(url: String?) {
        url?.let {
            viewModelScope.launch(Dispatchers.Main) {
                _effect.emit(Effect.NavigateToFile(url))
            }
        }
    }

    fun eventNavigateToFolder(path: String?) {

        if (path != null) {
            viewModelScope.launch(Dispatchers.Main) {
                _effect.emit(Effect.NavigateToFolder(ownerName, repositoryName, path))
            }
        }
    }
}