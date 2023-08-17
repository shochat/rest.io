package io.logic;

public interface PasswordGenerator {
    String generatePassword(int length, boolean withUpperCase, boolean withNumbers, boolean withSymbols);
    String generateForValue(String value, String key, int count, boolean withUppercase, boolean withNumbers, boolean withSymbols);
}
