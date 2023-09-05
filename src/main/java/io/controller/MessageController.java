package io.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {
    @PostMapping("/")
    public String postMessage() {
        return "post message";
    }

    @GetMapping("/{id}")
    public String getMessage(String id) {
        return "get message";
    }

    @GetMapping("/")
    public String getAllMessages() {
        return "post message";
    }

    @DeleteMapping("/{id}")
    public String deleteMessage(String id) {
        return "post message";
    }

    @PutMapping("/{rate}")
    public String rateMessage(boolean rate) {
        return "rate message";
    }
}
