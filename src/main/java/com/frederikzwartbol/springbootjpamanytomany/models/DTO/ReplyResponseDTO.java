package com.frederikzwartbol.springbootjpamanytomany.models.DTO;


import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.Tweet;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.user.User;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class ReplyResponseDTO implements Serializable {
    private Long id;
    private String message;
    private LocalDateTime publicationDate;
    private User user;
    private Tweet tweet;
}
