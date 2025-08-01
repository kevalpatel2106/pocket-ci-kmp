package com.kevalpatel2106.pocketci.selector.selector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevalpatel2106.pocketci.core.navigation.navigator.Navigator
import com.kevalpatel2106.pocketci.entity.CIType
import com.kevalpatel2106.pocketci.selector.navigation.SubGraphDestinations.Register
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class SelectorViewModel(
    private val navigator: Navigator,
    private val selectorRepository: SelectorRepository,
) : ViewModel() {

    private val _viewState = MutableStateFlow<SelectorViewState>(SelectorViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        loadCIList()
    }

    private fun loadCIList() = viewModelScope.launch {
        try {
            val supportedCI = selectorRepository.getSupportedCIInfo()
            _viewState.value = SelectorViewState.Success(
                items = supportedCI.map {
                    SelectorItem(
                        name = it.type.name,
                        isSelected = false,
                        ciType = it.type,
                    )
                }.toImmutableList(),
            )
        } catch (e: Exception) {
            ensureActive()
            _viewState.value = SelectorViewState.Error(e.message ?: "Unknown error")
        }
    }

    fun onRetry() {
        loadCIList()
    }

    fun onCiSelected(ciType: CIType) {
        _viewState.update {
            if (it is SelectorViewState.Success) {
                val items = it.items.map { item ->
                    item.copy(isSelected = item.ciType == ciType)
                }.toImmutableList()
                SelectorViewState.Success(items)
            } else {
                it
            }
        }
        viewModelScope.launch { navigator.navigate(Register(ciType)) }
    }
}