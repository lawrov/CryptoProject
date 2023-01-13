package com.cryptoproject;

import java.io.*;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.lang.Character.toLowerCase;

//такие классы лучше называть Util
public class StatMap {
    private SortedMap<Double,String> map = new TreeMap<Double,String>(Comparator.reverseOrder());
    private static final String ALPHABET = " абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!?0123456789";
    private String src;

    public void setSource(String src) {
        this.src = src;
    }

    public TreeMap getMap() {
        return makeMap();
    }

    private TreeMap makeMap() {
        int numberOfSimbols = 0;
        String inputString = null;
        String file = src;
        for (int i = 0; i < ALPHABET.length(); i++) {
            int count = 0;

            try (FileReader fileReader = new FileReader(file)) {
                try (BufferedReader reader = new BufferedReader(fileReader)) {
                    while (reader.ready()) {
                        inputString = reader.readLine();
                        if (i == 0) {
                            numberOfSimbols += inputString.length();
                        }
                        for (int j = 0; j < inputString.length(); j++) {
                            if (toLowerCase(inputString.charAt(j)) == ALPHABET.charAt(i)) {
                                count++;
                            }
                        }
                    }
                    map.put((1.0 * count * 100 / numberOfSimbols),String.valueOf(ALPHABET.charAt(i)));
                }
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found Or Unreadable!");
                System.out.println("============================");
                Menu.main();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Карта для статистического анализа создана!");
        return (TreeMap<Double,String>) map;
    }
}

