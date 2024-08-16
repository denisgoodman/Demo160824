package com.example.demo.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.demo.R
import com.example.demo.ui.models.Cell
import com.example.demo.ui.screens.home.components.ButtonCreate
import com.example.demo.ui.screens.home.components.CellItem
import com.example.demo.ui.screens.home.components.Header
import com.example.demo.ui.theme.deadIconGradient
import com.example.demo.ui.theme.liveIconGradient
import com.example.demo.ui.theme.livingIconGradient
import com.example.demo.ui.theme.screenBackgroundGradient

/**
 * @Author: Denis Gabov
 * @Date: 16.08.2024
 */

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
) {
    val state by viewModel.cellsState.collectAsState()
    val lazyListState = rememberLazyListState()

    LaunchedEffect(state) {
        lazyListState.scrollToItem(state.size)
    }

    Column(
        modifier = modifier
            .background(Brush.verticalGradient(screenBackgroundGradient))
            .padding(16.dp)
    ) {

        Header(title = stringResource(R.string.header_title))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 8.dp)
                .weight(1f),
            state = lazyListState,
        ) {
            items(state) { item: Cell ->
                when (item) {
                    Cell.DEAD -> CellItem(
                        icon = Icons.Default.Phone,
                        colors = deadIconGradient,
                        title = stringResource(R.string.dead_cell_title),
                        subtitle = stringResource(R.string.dead_cell_subtitle)
                    )

                    Cell.LIVING -> CellItem(
                        icon = Icons.Default.Face,
                        colors = livingIconGradient,
                        title = stringResource(R.string.living_cell_title),
                        subtitle = stringResource(R.string.living_cell_subtitle)
                    )

                    Cell.LIVE -> CellItem(
                        icon = Icons.Default.Build,
                        colors = liveIconGradient,
                        title = stringResource(R.string.live_cell_title),
                        subtitle = stringResource(R.string.live_cell_subtitle)
                    )
                }
            }
        }

        ButtonCreate(
            title = stringResource(R.string.button_create_title),
            onClick = { viewModel.create() }
        )
    }
}


