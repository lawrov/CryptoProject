package com.cryptoproject;

import java.io.*;
import java.util.*;

import static java.lang.Character.toUpperCase;

// * * * DECRYPTION BY MAPS * * *
public class StatAnalizer {
    private SortedMap<Double, String> map = new TreeMap<>();
    private SortedMap<Double, String> encMap = new TreeMap<>();
    private String src;
    private String dst;

    public void setDest(String src, String dst) {
        this.src = src;
        this.dst = dst;
    }

// ========= CREATING MAPS ============
    public void decodeByMap() {
        StatMap eMap = new StatMap();
        eMap.setSource(src);
        encMap = eMap.getMap();             // creating an encrypted file map
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя файла для сбора статистики: ");
        StatMap sMap = new StatMap();
        sMap.setSource(scanner.nextLine());
        map = sMap.getMap();                // creating a map of the original file (or part of file)
        //System.out.println(encMap);
        //System.out.println(map);

        String[] eArray = encMap.values().toArray(new String[0]);
        char[] encArray = new char[eArray.length];
        int index = 0;
        for (String e : eArray) {
            encArray[index++] = e.charAt(0);
        }
        //System.out.println("Encoded:  " + Arrays.toString(encArray));

        String[] statArray = map.values().toArray(new String[0]);
        char[] stArray = new char[statArray.length];
        index = 0;
        for (String e : statArray) {
            stArray[index++] = e.charAt(0);
        }
        //System.out.println("Original: " + Arrays.toString(stArray));

// ================ DECRYPTION ===============

        try (FileReader fileReader = new FileReader(src);
             BufferedReader reader = new BufferedReader(fileReader);
             FileWriter fileWriter = new FileWriter(dst);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            String inputString;
            while (reader.ready()) {
                inputString = reader.readLine();

                char[] outCharArray = inputString.toCharArray();
                for (int i = 0; i < outCharArray.length; i++) {
                    for (int j = 0; j < encArray.length; j++) {
                        if (outCharArray[i] == encArray[j]) {
                            if (j < stArray.length) {
                                outCharArray[i] = stArray[j];
                                break;
                            } else {
                                outCharArray[i] = '*';
                            }
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



