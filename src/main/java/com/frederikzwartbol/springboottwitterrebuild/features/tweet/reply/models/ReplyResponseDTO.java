package com.frederikzwartbol.springboottwitterrebuild.features.tweet.reply.models;


import com.frederikzwartbol.springboottwitterrebuild.features.tweet.Tweet;
import com.frederikzwartbol.springboottwitterrebuild.features.user.User;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ReplyResponseDTO {
    private Long id;
    private String message;
    private LocalDateTime publicationDate;
    private User user;
    private Tweet tweet;
}
