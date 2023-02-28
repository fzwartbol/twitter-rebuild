package com.frederikzwartbol.springboottwitterrebuild.features.conversation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frederikzwartbol.springboottwitterrebuild.features.user.User;
import com.frederikzwartbol.springboottwitterrebuild.features.conversation.models.Message;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Table(name = "`conversation`")
@Entity
@Getter
@Setter
@ToString
public class Conversation {
    @SequenceGenerator(name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Id
    private Long id;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Message> messages;

    @ManyToMany
            (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "sender_id",
            referencedColumnName = "userId"
    )
    @JsonIgnoreProperties(value = {"tweets","conversations"})
    @ToString.Exclude
    private Set<User> participants;

    public void sendMessage (Message message) {
        message.setConversation(this);
        messages.add(message);
    }

    public static Conversation startConversation(Set<User> participants) {
        var conversation = new Conversation(new ArrayList<>(),participants);
        participants.forEach( user -> user.addConversation(conversation));
        return conversation;
    }

    public Conversation(List<Message> messages, Set<User> participants) {
        this.messages = messages;
        this.participants = participants;
    }
    public Conversation() {
    }

}
