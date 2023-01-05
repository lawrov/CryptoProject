package com.cryptoproject;

import java.util.Scanner;

public class Destination {
    private int item;
    private String src;

    public Destination(int item) {
        this.item = item;
    }

    public String getSource() {
        System.out.print("Введите путь к исходному файлу: ");
        Scanner scanner = new Scanner(System.in);
        String src = scanner.nextLine();
        this.src = src;
        return src;
    }

    public String getDestination () {

        String dst = null;
            switch (item) {
                case 1:
                    dst = src.substring(0, src.lastIndexOf('\\') + 1) + "Encoded.txt";
                    break;
                case 2:
                    dst = src.substring(0, src.lastIndexOf('\\') + 1) + "Decoded.txt";
                    break;
            }
        return dst;
    }
}


