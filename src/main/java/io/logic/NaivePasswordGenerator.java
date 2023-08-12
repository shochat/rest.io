package io.logic;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class NaivePasswordGenerator implements PasswordGenerator {

    @Override
    public String generatePassword(int length, boolean withUpperCase, boolean withNumbers, boolean withSymbols) {
        String total = withUpperCase? "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz" :
                "abcdefghijklmnopqrstuvwxyz";
        if (withNumbers) {
            total += "0123456789";
        }
        if (withSymbols) {
            total += "!@#$%^&*()_+-=`~/?'|\\'.,<>[]{}";
        }
        return RandomStringUtils.random(length, total);
    }
}
