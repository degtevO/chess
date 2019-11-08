package com.company;

public class Main {

    public static void main(String[] args) {
        /*
         * Можно было по-школьному: введите ширину, введите высоту и так далее.
         * Задание все равно в другом, поэтому так для экономии времени
         */
        int x = 6;
        int y = 9;
        int kings = 3;
        int queens = 2;
        int bishops = 0;
        int horses = 5;
        int castles = 0;

        System.out.println("Starting.... Please wait for a few(or many) minutes");
        long time = System.currentTimeMillis();
        try {
            Game game = new Game(x, y, kings, queens, bishops, horses, castles);
            System.out.println(game.calculate());
            time = System.currentTimeMillis() - time;
            System.out.println(time / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
