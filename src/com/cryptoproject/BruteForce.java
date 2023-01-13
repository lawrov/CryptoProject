package com.cryptoproject;

import java.io.*;
import java.util.Scanner;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class BruteForce {
    //аналогично стоит вынести алфавит
    private final String ALPHABET = " абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!?0123456789";
    private String src;
    private String dst;
    private boolean next = true;
    int inputShift = 1;

    public void setDest(String src, String dst) {
        this.src = src;
        this.dst = dst;
    }

    public void decodeFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(src));
            String inputString = null;

            while (reader.ready()) {

                    if (next) {
                        inputString = reader.readLine();

                    }
                    next = false;
                    char[] alphabetArray = ALPHABET.toCharArray();
                    int shift;
                    char[] outCharArray = new char[inputString.length()];
                        if (inputShift > 51) {//тоже магическое число
                            System.out.println("=============================");
                            System.out.println("   Цикл подбора завершен");
                            System.out.println("Рекомендуется сменить строку!");
                            System.out.println("=============================");
                        inputShift = inputShift % 51;
                        }
                    for (int i = 0; i < inputString.length(); i++) {
                        for (int j = 0; j < alphabetArray.length; j++) {
                            if (toLowerCase(inputString.charAt(i)) == alphabetArray[j]) {
                                if (j - inputShift < 0) {
                                    shift = (j - inputShift) + 51;
                                } else shift = j - inputShift;
                                outCharArray[i] = alphabetArray[shift];
                            }
                        }
                    }
                    examResult(outCharArray);
                        inputShift++;
                }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Or Unreadable!");
            System.out.println("============================");
            Menu.main();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

// ============ CHECK RESULT =============
    private void examResult(char[] inputCharArray) {
        //лучше этот словарь вынести из метода, чтобы нам было проще его изменять
        String[] dictionary = new String[]{" и "," в "," а "," на "," с "," из "," но "," если "," тоже "," что "}; // condition set ". ", ": ", "! ", "? ",
        String inputString = new String(inputCharArray);
        if (!"".equals(inputString)) {
            boolean flag = false;
            for (int i = 0; i < dictionary.length; i++) {
                if (inputString.toLowerCase().contains(dictionary[i])) {
                    //можно одним вызовом принтлн
                    System.out.println("CURRENT KEY: " + (inputShift));
                    System.out.println("----------------------------");
                    System.out.println("Пример дешифрованного текста:");
                    if (inputString.length() > 75) {
                        inputString = inputString.substring(0, 75);
                    }
                    System.out.println("« " + inputString + " »");

                    Scanner scanner = new Scanner(System.in);
                    System.out.println("=====================");
                    System.out.println("<Enter> - Продолжить подбор");
                    System.out.println("1 - Сменить строку для подбора");
                    System.out.println("2 - Сохранить результат в файл");
                    System.out.println("------------------------------");
                    System.out.println("0 - Выход в главное меню");

                    while (!flag) {
                        switch (scanner.nextLine()) {
                            case "2":
                                System.out.println("-----------------------");
                                System.out.println("Сохраняем...");
                                FileDecoder fd = new FileDecoder();
                                fd.setShift(inputShift);
                                fd.setDest(src, dst);
                                fd.decodeFile();
                                break;
                            case "":
                                System.out.println("-----------------------");
                                System.out.println("Продолжаем подбор ключа...");
                                flag = true;
                                break;
                            case "1":
                                System.out.println("-----------------------");
                                System.out.println("Подбор ключа c новой строки...");
                                next = true;
                                flag = true;
                                inputShift = 0;
                                break;
                            case "0":
                                Menu.main();
                                break;
                            default:
                                System.out.println("Сделайте выбор");
                                flag = false;
                                break;
                        }
                    }
                }
                if(flag) break;
                if (inputShift == 51) {
                    next = true;
                    inputShift = 0;
                    break;
                }
            }
        } else {
            System.out.println("SORRY, THIS STRING IS EMPTY");
            next = true;
        }

    }
}
