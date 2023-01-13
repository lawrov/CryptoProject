package com.cryptoproject;

import java.util.SortedMap;
import java.util.TreeMap;

public class DecoderByKey {

    private SortedMap<Double, String> map = new TreeMap<>();

    //тоже стоит вынести алфавит отдельно
    private final String ALPHABET = " абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!?0123456789";
    private String src;
    private String dst;

    public void setDest(String src, String dst) {
        this.src = src;
        this.dst = dst;
    }

// ========== SEARCH KEY VALUE ============
    public void getShift() {
        StatMap sMap = new StatMap();
        sMap.setSource(src);
        map = sMap.getMap();
        String symbol = map.get(map.firstKey());
        int index = ALPHABET.indexOf(symbol);
        System.out.println("Ключ дешифрования: " + index);
        FileDecoder fDecoder = new FileDecoder();
        fDecoder.setDest(src,dst);
        fDecoder.setShift(index);
        fDecoder.decodeFile();
    }
}
