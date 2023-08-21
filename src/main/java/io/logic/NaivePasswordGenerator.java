package io.logic;

import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class NaivePasswordGenerator implements PasswordGenerator {
    final private static String symbols = "!@#$%^&*(){}[]<>.|\\:;?/,";
    final private static String lowerCase = "abcdefghijklmnopqrstuvwxyz";
    final private static String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final private static String numbers = "0123456789";

    @Override
    public String generatePassword(int length, boolean withUpperCase, boolean withNumbers, boolean withSymbols) {
        String total = getAllowedChars(withUpperCase, withNumbers, withSymbols);
        return RandomStringUtils.random(length, total);
    }

    @Override
    @SneakyThrows
    public String generateForValue(String value, int count, boolean withUppercase, boolean withNumbers, boolean withSymbols) {
        final MessageDigest sha3 = MessageDigest.getInstance("SHA3-256");
        byte[] bytes = sha3.digest(value.getBytes(StandardCharsets.UTF_8));

        String hashString = new BigInteger(bytes).toString(36);

        if ( ! withUppercase && ! withSymbols) {
            return hashString.substring(0, count);
        }
        return insertUpperCaseAndSymbols(hashString, withUppercase, withSymbols).substring(0, count);
    }

    private String insertUpperCaseAndSymbols(String hashString,boolean withUppercase, boolean withSymbols) {
        char[] charArray = hashString.toCharArray();
        int symbolsIndex = 0;
        for (int i = 0; i < charArray.length - 1; i++) {
            if (withUppercase && Character.isLetter(charArray[i]) && i % 2 == 0) {
                charArray[i] = Character.toUpperCase(charArray[i]);
            }
            if (withSymbols && Character.isDigit(charArray[i]) && i % 3 == 0) {
                charArray[i] = symbols.toCharArray()[symbolsIndex];
                symbolsIndex = ++symbolsIndex % symbols.length();
            }
        }
        return String.valueOf(charArray);
    }

    private static String getAllowedChars(boolean withUpperCase, boolean withNumbers, boolean withSymbols) {
        
        String total = withUpperCase ? lowerCase + upperCase : lowerCase;
        if (withNumbers) {
            total += numbers;
        }
        if (withSymbols) {
            total += symbols;
        }
        return total;
    }
}
