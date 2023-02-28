package com.frederikzwartbol.springboottwitterrebuild.features.conversation.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frederikzwartbol.springboottwitterrebuild.features.conversation.Conversation;
import com.frederikzwartbol.springboottwitterrebuild.features.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "`message`")
@Entity
@Getter
@Setter
@ToString
public class Message {
    @SequenceGenerator(name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Id
    private Long id;
    private String message;
    private LocalDateTime sendDate;

    @ManyToOne
            (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "sender_id",
            referencedColumnName = "userId"
    )
    @JsonIgnoreProperties(value = {"tweets","followers","following","conversations"})
    @ToString.Exclude
    private User sender;

    public Message(String message, LocalDateTime sendDate, User sender) {
        this.message = message;
        this.sendDate = sendDate;
        this.sender = sender;
    }

    @ManyToOne
    @JoinColumn(name = "conversation_conversation_id")
    @ToString.Exclude
    @JsonIgnore
    private Conversation conversation;

    public Message() {
    }
}
