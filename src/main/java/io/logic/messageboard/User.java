package io.logic.messageboard;

import io.model.message.Message;
import lombok.Data;

import java.util.List;

@Data
public class User {
    private String name;
    private String hashedPassword;
    private List<Message> messages;
}
