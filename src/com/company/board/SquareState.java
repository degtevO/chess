package com.company.board;

/**
 * Created by Олег on 02.06.2017.
 */

/**
 * Состояние клеток доски
 */
public enum SquareState {
    FREE,       // Свободная клетка
    DISABLED,   // Клетка, в которую запрещено ставить фигуру
    TAKEN       // Клетка, занятая фигурой
}
