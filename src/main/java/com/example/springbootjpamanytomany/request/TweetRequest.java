package com.example.springbootjpamanytomany.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TweetRequest {
    private final Long tweetId;
    private final String title;
    private final String message;
    private final Long userId;
    private final String hashtag;
}
