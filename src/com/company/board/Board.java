package com.company.board;

/**
 * Created by Олег on 02.06.2017.
 */

/**
 * Класс представляет собой шахматную доску
 */
public class Board {
    private int width;
    private int height;
    private SquareState[][] states;
    private SquareState[][] prevStates; // Pattern memento

    public Board(int x, int y) throws Exception {
        if(x <= 0 || y <= 0) {
            throw new Exception();
        }
        this.width = x;
        this.height = y;
        states = new SquareState[x][y];
        prevStates = new SquareState[x][y];
        initSquares(x, y);
    }

    /**
     * Геттер с логикой. Возвращает клон клеток доски. В паре с похожим сеттером
     * делает возможным сохранение и восстановление состояния доски
     * @return клон клеток доски
     */
    public SquareState[][] getStates() {
        SquareState[][] statesClone = new SquareState[width][height];

        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                statesClone[x][y] = states[x][y];
            }
        }

        return statesClone;
    }

    /**
     * Тот самый сеттер.
     * @param states состояние клеток доски
     */
    public void setStates(SquareState[][] states) {
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                this.states[x][y] = states[x][y];
            }
        }
    }

    /**
     * Memento
     */
    public void saveStates() {
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                prevStates[x][y] = states[x][y];
            }
        }
    }

    /**
     * Memento
     */
    public void rollbackStates() {
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                states[x][y] = prevStates[x][y];
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public SquareState getSquareState(int x, int y) {
        return states[x][y];
    }

    public void setSquareState(int x, int y, SquareState state) {
        states[x][y] = state;
    }

    /**
     * Инициализируем все клетки доски
     * @param width ширина доски
     * @param height высота доски
     */
    private void initSquares(int width, int height) {
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                states[x][y] = SquareState.FREE;
            }
        }
    }
}
