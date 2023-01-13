package com.cryptoproject;

import java.io.*;
import java.util.Scanner;
import static java.lang.Character.toLowerCase;

public class FileEncoder {

    //алафавит придеться пару раз дублировать, лучше его вынести в класс Util
    private final String ALPHABET = " абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!?0123456789";
    private String src;
    private String dst;

    public void setDest(String src, String dst) {
        this.src = src;
        this.dst = dst;
    }
    public void encodeFile() {
            int inputShift = 0;
        try (FileReader fileReader = new FileReader(src);
             BufferedReader reader = new BufferedReader(fileReader);
        FileWriter fileWriter = new FileWriter(dst);
        BufferedWriter writer = new BufferedWriter(fileWriter)) {
            String inputString;
            System.out.print("Введите количество сдвигов: ");
            Scanner scanner = new Scanner(System.in);
            if (!scanner.hasNextInt()) {
                System.out.println("Введите число!");
            } else {
                inputShift = scanner.nextInt();        // number of shifts
                if (inputShift > 50) {//50 это магическое число, никто не знает что оно значит. Его надо вынести в перменную с понятным названием и если надо комментарием
                    inputShift = inputShift % 50;
                    System.out.println("Real Key: " + inputShift);
                }
            }
            while (reader.ready()) {
                inputString = reader.readLine();
                char[] alphabetArray = ALPHABET.toCharArray();
                int shift = 0;
                char[] outCharArray = new char[inputString.length()];
                for (int i = 0; i < inputString.length(); i++) {
                    for (int j = 0; j < alphabetArray.length; j++) {
                        if (toLowerCase(inputString.charAt(i)) == alphabetArray[j]) {
                            if (j + inputShift > 50) {
                                shift = (j + inputShift) % 51;//50 лучше отдельной переменной, поэтому тут лучше указать эту пременную + 1
                            } else shift = j + inputShift;
                            outCharArray[i] = alphabetArray[shift];
                        }
                    }
                }
                String outputString = new String(outCharArray);
                writer.write(outputString);
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("File Not Found Or Unreadable!");
            System.out.println("============================");
            Menu.main();
        }
        System.out.println("Зашифрованный файл: '" + dst + "' создан!");
        System.out.println("============================");
        Menu.main();
    }
}
