package com.frederikzwartbol.springboottwitterrebuild.features.conversation.models;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record MessageRequest(
        Long id,
        String message,
//        LocalDateTime sendDate,
        @NotNull(message = "Sender id must not be null") Long senderId,
        @NotNull(message = "Conversation id must not be null") Long conversationId
) {
}
