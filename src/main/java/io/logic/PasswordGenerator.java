package io.logic;

public interface PasswordGenerator {
    String generatePassword(int length, boolean withUpperCase, boolean withNumbers, boolean withSymbols);
}
