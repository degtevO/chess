package com.company.chessman;

import com.company.board.Board;
import com.company.board.SquareState;

/**
 * Created by Олег on 02.06.2017.
 */

/**
 * Король
 */
public class King extends AbstractChessman {

    @Override
    protected boolean disableSquares(int x, int y, Board board) {
        // Сохраняем состояние доски, на случай если выяснится, что фигуру на эту точку поставить было нельзя.
        board.saveStates();
        int[][] moves = {{1,1}, {-1,1}, {-1,-1}, {1,-1}, {0,1}, {-1,0}, {0,-1}, {1,0}};
        for (int[] move: moves) {
            int tmpX = x + move[0];
            int tmpY = y + move[1];

            if (tmpX < 0 || tmpY < 0 || tmpX >= board.getWidth() || tmpY >= board.getHeight()) {
                continue;
            }

            // Если клетка занята другой фигурой
            if (board.getSquareState(tmpX, tmpY) == SquareState.TAKEN) {
                board.rollbackStates();
                return false;
            }

            board.setSquareState(tmpX, tmpY, SquareState.DISABLED);
        }

        return true;
    }
}
