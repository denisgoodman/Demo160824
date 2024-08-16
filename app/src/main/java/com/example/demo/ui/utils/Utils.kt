package com.example.demo.ui.utils

import com.example.demo.ui.models.Cell

/**
 * @Author: Denis Gabov
 * @Date: 16.08.2024
 */

/**
 * Update cells list with auto adding to tail Cell.LIVE when last 3 is Cell.LIVING,
 * after adding 3 Cell.DEAD previous Cell.LIVE replaced to Cell.Dead
 * when add new Cell.LIVING: .., LIVING, LIVING, LIVING --> .., LIVING, LIVING, LIVING, LIVE
 * when add new Cell.DEAD: .., LIVE, DEAD, DEAD --> .., DEAD, DEAD, DEAD, DEAD
 * @param cells previous cells list
 * @param newCell random generated Cell. Should be Cell.LIVING or Cell.DEAD
 * @return updated cells
 */
fun updateCells(cells: List<Cell>, newCell: Cell): List<Cell>{
    val updatedCells = cells.plus(newCell)

    val hasLastThreeLivingCell = updatedCells.size >= 3 && updatedCells.takeLast(3).all { it == Cell.LIVING }
    val hasLastThreeDeadCell = updatedCells.size >= 3 && updatedCells.takeLast(3).all { it == Cell.DEAD }
    val hasLiveCellBeforeThreeDead = updatedCells.size >= 4 && updatedCells.takeLast(4).any { it == Cell.LIVE }

    if (hasLastThreeLivingCell){
        return updatedCells.plus(Cell.LIVE)
    }

    if (hasLastThreeDeadCell && hasLiveCellBeforeThreeDead){
       return updatedCells.mapIndexed { index, cell ->
           if (index == updatedCells.size - 4) Cell.DEAD else cell
       }

    }
    return updatedCells
}