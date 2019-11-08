package com.company.chessman;

import com.company.board.Board;
import com.company.board.SquareState;

/**
 * Created by Олег on 02.06.2017.
 */

/**
 * Класс представляет собой шахматную фигуру
 */
public abstract class AbstractChessman {
    // Начиналось как что-то вроде цепочки обязанностей.
    // У первой фигуры вызывается движение (Метод move).
    // Она передаёт его дальше, пока те могут ходить, потом ходит сама.
    // Каждая фигура(кроме последней) содержит следующую фигуру
    private AbstractChessman next;

    public void setNext(AbstractChessman chessman) {
        this.next = chessman;
    }

    /**
     * Метод пытается пометить другие клетки доски, в которые нельзя ставить фигуры, если поставить фигуру по координатам
     * Если нарывается на другую фигуру в той клетке, которую пытается пометить как запрещенная, значит нельзя
     * поставить фигуру по этим координатам, поэтому откатывает изменения, которые успел сделать(Memento).
     * Каждый тип фигуры реализовывает по-своему.
     * @param x координата x
     * @param y координата y
     * @param board Шахматная доска
     * @return Возвращет результат. Можно ли поставить сюда фигуру
     */
    protected abstract boolean disableSquares(int x, int y, Board board);

    /**
     * Начинает движение фигур по доске.
     * @param board Шахматная доска
     * @return Количество доступных комбинаций для этого уровня вложенности фигур
     */
    public long move(Board board) {
        return _move(board, 0, 0);
    }

    /**
     * Перегрузка метода, когда нужно начать движение с определённой клетки
     * @param board Шахматная доска
     * @param initX Координата x
     * @param initY Координата y
     * @return Количество доступных комбинаций для этого уровня вложенности фигур
     */
    public long move(Board board, int initX, int initY) {
        return _move(board, initX, initY);
    }

    /**
     * Реализация движения фигур
     * @param board Шахматная доска
     * @param initX Координата x
     * @param initY Координата y
     * @return Количество доступных комбинаций для этого уровня вложенности фигур
     */
    private long _move(Board board, int initX, int initY) {
        long result = 0;
        // Сохраним состояние шахматной доски перед каждым шагом фигуры
        SquareState[][] states = board.getStates();

        for (int x = initX; x < board.getWidth(); ++x) {
            for (int y = x == initX ? initY : 0; y < board.getHeight(); ++y) {
                // Если клетка не свободна, или нельзя поставить сюда фигуру,
                // так как она начнет бить другие фигуры
                if (board.getSquareState(x, y) != SquareState.FREE || !disableSquares(x, y, board)) {
                    continue;
                }

                board.setSquareState(x, y, SquareState.TAKEN);

                // После каждого шага этой фигуры, выполняем все шаги всех вложенных фигур.
                // Аккумулируем количество доступных комбинаций на этом шаге
                if (next != null) {
                    if (next.getClass() == this.getClass()) {
                        result += next.move(board, x, y);
                    }
                    else {
                        result += next.move(board, 0, 0);
                    }
                }
                // Если вложенных фигур нет, значит это последняя фигура. И если ей удалось встать - значит это еще одна
                // правильная с точки зрения задания комбинация фигур.
                else {
                    ++result;
                }

                // После каждого шага на этом уровне вложенности, возвращаем изменения доски на состояние
                // до шага фигуры
                board.setStates(states);
            }
        }

        return result;
    }
}
