package com.tcg.radixworksheetgenerator;

public enum Base {
    BINARY(2, "bin"),
    DECIMAL(10, "dec"),
    HEXADECIMAL(16, "hex");
    public final int radix;
    public final String subScript;

    Base(int radix, String subScript) {
        this.radix = radix;
        this.subScript = subScript;
    }
}
