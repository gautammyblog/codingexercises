package com.gauts.exercises;

public class Test {
    public static void main(String[] args) {
        Test app = new Test();
        System.out.println(app.checkIfStringHasUniqueChars("abadef"));
    }

    public boolean checkIfStringHasUniqueChars(String str) {
        boolean[] flags = new boolean[256];
        char[] strChars = str.toCharArray();
        for (int i = 0; i < strChars.length; i++) {
            if (flags[strChars[i]]) {
                return false;
            }
            flags[strChars[i]] = true;
        }
        return true;
    }
}
