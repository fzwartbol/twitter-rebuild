package com.frederikzwartbol.springbootjpamanytomany.models.request;

public record TweetRequest(Long tweetId, String message, Long userId, String category) {
}
