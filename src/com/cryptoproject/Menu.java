package com.cryptoproject;

import java.util.Scanner;

//идея хорошая, но комментарии для описания класса пишутся черещ 2 *, тогда по ним потом можно автоматически сгенерировать документацию
/**
* * * MAIN MENU * * *
* выбор режима работы программы
*/

public class Menu {

    //стоит сделать переменную финальной, этого просит идейка
    private Destination dst = new Destination(0);

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        //все это можно вывести одним принтлн
        System.out.println("Выбор режима работы программы:");
        System.out.println("1 - Режим кодирования по ключу");
        System.out.println("2 - Режим декодирования по ключу");
        System.out.println("3 - Режим декодирования 'Brute-force'");
        System.out.println("4 - Режим декодирования 'стат. анализ'");
        System.out.println("5 - Выход");
        //енкодеры, брутфорс и декодеры лучше сделать статичными методами, создавать обьекты ни к чему, у них нет состояния и они нужны только для вызовы одной функции - т.е. их можно и заменить этой функцией
        while (true) {
                switch (scanner.nextLine()) {
                    case "1":
                        //тоже одним принтлн
                        System.out.println("============================");
                        System.out.println("Режим кодировщика...");
                        Destination dst = new Destination(1);
                        FileEncoder fileEncoder = new FileEncoder();
                        fileEncoder.setDest(dst.getSource(), dst.getDestination());
                        fileEncoder.encodeFile();
                        break;
                    case "2":
                        //аналогично
                        System.out.println("============================");
                        System.out.println("Режим декодировщика по ключу...");
                        FileDecoder fileDecoder = new FileDecoder();
                        dst = new Destination(2);
                        fileDecoder.setDest(dst.getSource(), dst.getDestination());
                        fileDecoder.decodeFile();
                        break;
                    case "3":
                        System.out.println("============================");
                        System.out.println("Режим 'Brute-force'...");
                        BruteForce bruteForce = new BruteForce();
                        dst = new Destination(2);
                        bruteForce.setDest(dst.getSource(), dst.getDestination());
                        bruteForce.decodeFile();
                        break;
                    case "4":
                        System.out.println("============================");
                        System.out.println("Режим статистического анализатора...");
                        System.out.println("1 - Собрать статистику из файла");
                        System.out.println("2 - Статистический подбор ключа");
                        while(true) {
                            switch (scanner.nextLine()) {
                                case "1":
                                    dst = new Destination(2);
                                    StatAnalizer statAnalizer = new StatAnalizer();
                                    statAnalizer.setDest(dst.getSource(), dst.getDestination());
                                    statAnalizer.decodeByMap();
                                    break;
                                case "2":
                                    dst = new Destination(2);
                                    DecoderByKey decoderByKey = new DecoderByKey();
                                    decoderByKey.setDest(dst.getSource(), dst.getDestination());
                                    decoderByKey.getShift();
                                    break;
                                default:
                                    System.out.println("Введите корректное значение");
                                    break;
                            }
                        }
                    case "5":
                        System.exit(0);
                    default:
                        System.out.println("Введите корректное значение");
                }
            }
    }
}

