package io.logic.password;

public interface PasswordGenerator {
    String generatePassword(int length, boolean withUpperCase, boolean withNumbers, boolean withSymbols);
    String generateForValue(String value, int count, boolean withUppercase, boolean withNumbers, boolean withSymbols);
}
