package com.example.springbootjpamanytomany.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class LikeRequest {
    private final Long tweetId;
    private final Long userId;
//    private final LocalDateTime likedDate;
}
