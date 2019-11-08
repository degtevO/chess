package com.company.chessman;

import com.company.board.Board;
import com.company.board.SquareState;

/**
 * Created by Олег on 02.06.2017.
 */

/**
 * Ладья
 */
public class Castle extends AbstractChessman {

    @Override
    protected boolean disableSquares(int x, int y, Board board) {
        // Сохраняем состояние доски, на случай если выяснится, что фигуру на эту точку поставить было нельзя.
        board.saveStates();

        // Мы дизаблим клеточки по 4-м направлениям.
        // Этот массив показывает, какие направления вышли за пределы доски
        boolean[] outs = {false, false, false, false};
        int[][] moves = {{0,1}, {-1,0}, {0,-1}, {1,0}};
        int delta = 1;
        int tmpX;
        int tmpY;

        while(true) {
            int count = 0;
            // По каждому из 4 направлений
            for(int i = 0; i < moves.length; ++i) {
                // Если по этому направлению мы вышли за пределы доски
                if(outs[i]) {
                    count++;
                    continue;
                }
                tmpX = x + delta * moves[i][0];
                tmpY = y + delta * moves[i][1];
                if (tmpX < 0 || tmpY < 0 || tmpX >= board.getWidth() || tmpY >= board.getHeight()) {
                    // Вышли по этому направлению за пределы доски
                    outs[i] = true;
                    continue;
                }

                // Если клетка занята другой фигурой
                if (board.getSquareState(tmpX, tmpY) == SquareState.TAKEN) {
                    board.rollbackStates();
                    return false;
                }

                board.setSquareState(tmpX, tmpY, SquareState.DISABLED);
            }
            delta++;
            // Если по всем 4 направлениям вышли за пределы доски, то заканчиваем цикл
            if(count == moves.length) {
                break;
            }
        }

        return true;
    }
}
