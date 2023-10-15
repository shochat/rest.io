package io.controller;

import io.logic.password.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private PasswordGenerator passwordGenerator;

    @GetMapping("/generate")
    public String generatePassword(
            @RequestParam(name = "count", required = false, defaultValue = "10") int numberOfCharacters,
            @RequestParam(name = "u", required = false, defaultValue = "false") boolean withUppercase,
            @RequestParam(name = "n", required = false, defaultValue = "false") boolean withNumbers,
            @RequestParam(name = "s", required = false, defaultValue = "false") boolean withSymbols) {

        return passwordGenerator.generatePassword(numberOfCharacters, withUppercase, withNumbers, withSymbols);
    }

    @GetMapping("/forValue")
    public String generateForValue(
            @RequestParam(name = "value") String value,
            @RequestParam(name = "length", required = false, defaultValue = "10") int length,
            @RequestParam(name = "u", required = false, defaultValue = "false") boolean withUppercase,
            @RequestParam(name = "n", required = false, defaultValue = "false") boolean withNumbers,
            @RequestParam(name = "s", required = false, defaultValue = "false") boolean withSymbols) {

        return passwordGenerator.generateForValue(value, length, withUppercase, withNumbers, withSymbols);
    }


}
