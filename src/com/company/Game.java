package com.company;

import com.company.board.Board;
import com.company.chessman.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Олег on 02.06.2017.
 */

/**
 * Класс представляет собой текущую игру с заданными параметрами доски и количеством фигурок
 */
public class Game {
    private Board board;
    private AbstractChessman chessmansChain;

    public Game(int width, int height, int kings, int queens, int bishops, int horses, int castles) throws Exception {
        initBoard(width, height);
        initChessmen(kings, queens, bishops, horses, castles);
    }

    /**
     * Инициализация шахматной доски
     * @param width Ширина доски
     * @param height Высота доски
     * @throws Exception
     */
    private void initBoard(int width, int height) throws Exception {
        board = new Board(width, height);
    }

    /**
     * Инициализация шахматных фигурок. Строит из них цепочку
     * @param kings Количество королей
     * @param queens Количество ферзей
     * @param bishops Количество слонов
     * @param horses Количество коней
     * @param castles Количество ладей
     * @throws Exception
     */
    private void initChessmen(int kings, int queens, int bishops, int horses, int castles) throws Exception {
        if (kings < 0 || queens < 0 || bishops < 0 || horses < 0 || castles < 0) {
            throw new Exception("Invalid incoming arguments!");
        }

        int size = kings + queens + bishops + horses + castles;

        if (size == 0) {
            throw new Exception("No chessmen!");
        }

        // Сложим все проинициализированные фигурки в список
        List<AbstractChessman> chessmen = new ArrayList<>(size);
        for (int i = 0; i < kings; ++i) {
            chessmen.add(new King());
        }

        for (int i = 0; i < queens; ++i) {
            chessmen.add(new Queen());
        }

        for (int i = 0; i < bishops; ++i) {
            chessmen.add(new Bishop());
        }

        for (int i = 0; i < horses; ++i) {
            chessmen.add(new Horse());
        }

        for (int i = 0; i < castles; ++i) {
            chessmen.add(new Castle());
        }

        // Сделаем цепочку фигурок из списка
        for (int i = 0; i < chessmen.size() - 1; ++i) {
            chessmen.get(i).setNext(chessmen.get(i + 1));
        }

        chessmansChain = chessmen.get(0);
    }

    /**
     * Подсчет количества правильных с точки зрения условия задачи комбинаций для текущей игры
     * @return Количество комбинаций
     */
    public long calculate() {
        return chessmansChain.move(board);
    }
}
