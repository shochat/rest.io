package io.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/password")
public class PasswordController {
    @GetMapping("/generate")
    public String generatePassword(
            @RequestParam(name = "n", required = false, defaultValue = "10") int numberOfCharacters,
            @RequestParam(name = "u", required = false, defaultValue = "false") boolean withUppercase,
            @RequestParam(name = "withNumbers", required = false, defaultValue = "false") boolean withNumbers,
            @RequestParam(name = "s", required = false, defaultValue = "false") boolean withSymbols) {
        return String.format("%s-%s-%s-%s", numberOfCharacters,withUppercase, withNumbers, withSymbols);
    }
}
