package com.cryptoproject;

import java.io.*;
import java.util.Scanner;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

//все аналогично как и в енкодере
public class FileDecoder {
    private final String ALPHABET = " абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!?0123456789";
    private String src;
    private String dst;
    private int inputShift = 0;

    public void setShift(int shift) {
        inputShift = shift;
    }
    public void setDest(String src, String dst) {
        this.src = src;
        this.dst = dst;
    }
    public void decodeFile() {

        try (FileReader fileReader = new FileReader(src);
             BufferedReader reader = new BufferedReader(fileReader);
             FileWriter fileWriter = new FileWriter(dst);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            String inputString;
            if (inputShift == 0) {
                System.out.print("Введите количество сдвигов: ");
                Scanner scanner = new Scanner(System.in);
                if (!scanner.hasNextInt()) {
                    System.out.println("Введите число!");
                } else {
                    inputShift = scanner.nextInt();             // number of shifts
                    if (inputShift > 50) {
                        inputShift = inputShift % 50;
                        System.out.println("Real Key: " + inputShift);
                    }
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
                                if (j - inputShift < 0) {
                                    shift = (j - inputShift) + 51;
                                } else shift = j - inputShift;
                                outCharArray[i] = alphabetArray[shift];
                            }
                        }
                    }
                    if (outCharArray.length > 0) {
                        outCharArray[0] = toUpperCase(outCharArray[0]);
                        for (int i = 0; i < outCharArray.length; i++) {
                            if (i - 2 > 0) {
                                if ('.' == outCharArray[i - 2] || '!' == outCharArray[i - 2] || '?' == outCharArray[i - 2]) {
                                    outCharArray[i] = toUpperCase(outCharArray[i]);
                                }
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
        System.out.println("Дешифрованный файл: '" + dst + "' создан!");
        System.out.println("============================");
        Menu.main();
    }
}
