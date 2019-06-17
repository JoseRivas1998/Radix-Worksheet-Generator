package com.tcg.radixworksheetgenerator;

import java.util.Random;

public class Util {

    private enum UtilHolder {
        INSTANCE;
        final Random random;
        UtilHolder() {
            this.random = new Random();
        }
    }

    public static int random(int min, int max) {
        return UtilHolder.INSTANCE.random.nextInt(max - min + 1) + min;
    }

}
