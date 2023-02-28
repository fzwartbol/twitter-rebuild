package com.frederikzwartbol.springboottwitterrebuild.features.conversation.models;

import javax.validation.constraints.NotNull;

public record MessageDto(
        Long id,
        String message,
//        LocalDateTime sendDate,
        @NotNull(message = "Sender id must not be null") Long senderId,
        @NotNull(message = "Conversation id must not be null") Long conversationId
) {
}
