package io.model.message;

import lombok.Data;

@Data
public class Message {
    private int id;
    private String text;
    private int voteRate;
}
