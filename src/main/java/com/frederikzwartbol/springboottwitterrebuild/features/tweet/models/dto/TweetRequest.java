package com.frederikzwartbol.springboottwitterrebuild.features.tweet.models.dto;

public record TweetRequest(Long tweetId,
                           String message,
                           Long userId,
                           String category,
                           boolean containsMedia,
                           String mediaUrl,
                           String mediaType
                           ) {
}
