package com.frederikzwartbol.springboottwitterrebuild.features.conversation;

import com.frederikzwartbol.springboottwitterrebuild.features.conversation.models.Message;
import com.frederikzwartbol.springboottwitterrebuild.features.conversation.models.MessageRequest;
import com.frederikzwartbol.springboottwitterrebuild.features.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ConversationMapper {

    private final UserService userService;

    public Message DtoToEntity (MessageRequest messageRequest) {
        return  new Message(
                messageRequest.message(),
                LocalDateTime.now(),
                userService.findUserById(messageRequest.senderId())
        );
    }
}
