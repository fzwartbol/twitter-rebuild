package com.frederikzwartbol.springboottwitterrebuild.features.user.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frederikzwartbol.springboottwitterrebuild.features.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "`message`")
@Entity
@Getter
@Setter
@ToString
public class Message {
    @Id
    @Column(name = "message_id")
    private Long id;
    private String message;
    private LocalDateTime sendDate;

    @ManyToOne
            (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "sender_id",
            referencedColumnName = "userId"
    )
    @JsonIgnoreProperties("tweets")
    private User sender;
    @ManyToOne
            (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "receiver_id",
            referencedColumnName = "userId"
    )
    @JsonIgnoreProperties("tweets")
    private User receiver;

}
