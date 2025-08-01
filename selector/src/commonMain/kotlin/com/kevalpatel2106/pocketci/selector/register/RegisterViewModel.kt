package com.kevalpatel2106.pocketci.selector.register

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.kevalpatel2106.pocketci.selector.navigation.SubGraphDestinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class RegisterViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val selectedCIType = savedStateHandle.toRoute<SubGraphDestinations.Register>().ciType

    private val _viewState = MutableStateFlow(0)
    val viewState = _viewState.asStateFlow()
}