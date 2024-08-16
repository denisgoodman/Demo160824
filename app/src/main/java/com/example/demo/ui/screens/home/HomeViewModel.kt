package com.example.demo.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.demo.ui.utils.updateCells
import com.example.demo.ui.models.Cell
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * @Author: Denis Gabov
 * @Date: 16.08.2024
 */

class HomeViewModel : ViewModel() {
    private val _cellsState = MutableStateFlow(emptyList<Cell>())
    val cellsState = _cellsState.asStateFlow()

    fun create() {
        val randomCell = listOf(Cell.DEAD, Cell.LIVING).random()
        _cellsState.update { updateCells(it, randomCell) }
    }
}

