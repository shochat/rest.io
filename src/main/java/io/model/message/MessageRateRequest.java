package io.model.message;

import lombok.Data;

@Data public class MessageRateRequest {
    Long messageId;
    boolean rate;
}
