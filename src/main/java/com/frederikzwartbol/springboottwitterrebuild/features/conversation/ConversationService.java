package com.frederikzwartbol.springboottwitterrebuild.features.conversation;

import com.frederikzwartbol.springboottwitterrebuild.exceptions.exceptions.GenericNotFoundException;
import com.frederikzwartbol.springboottwitterrebuild.features.conversation.models.Message;
import com.frederikzwartbol.springboottwitterrebuild.features.conversation.models.MessageRequest;
import com.frederikzwartbol.springboottwitterrebuild.features.conversation.models.StartConversationRequest;
import com.frederikzwartbol.springboottwitterrebuild.features.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserService userService;
    private final ConversationMapper conversationMapper;

    public Conversation findConversationById(Long conversation) {
        return conversationRepository.findById(conversation)
                .orElseThrow(() -> new GenericNotFoundException("Conversation not found"));
    }

    public Conversation startConversation (StartConversationRequest startConversationRequest) {
        var users = startConversationRequest.userIds().stream().map(id ->  userService.findUserById(id))
                .collect(Collectors.toSet());
        var conversation = Conversation.startConversation(users);
        return conversationRepository.save(conversation);
    }

    public Conversation addMessageToConversation(MessageRequest messageRequest){
        log.info("test {}", messageRequest);
        var conversation = findConversationById(messageRequest.conversationId());
        log.info("{}{}",messageRequest,conversation);

        conversation.sendMessage(conversationMapper.DtoToEntity(messageRequest));
        log.info("Sending message: {} in conversation {}",messageRequest,conversation);
        return conversationRepository.save(conversation);
    }


}
