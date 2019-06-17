package com.tcg.radixworksheetgenerator;

public enum Difficulty {
    EASY(0xF, 0xFF),
    MEDIUM(0xFF, 0xFFF),
    HARD(0xFFF, 0xFFFF);
    public final int LOWEST_NUMBER;
    public final int HIGHEST_NUMBER;

    Difficulty(int lowest_number, int highest_number) {
        LOWEST_NUMBER = lowest_number;
        HIGHEST_NUMBER = highest_number;
    }
}
