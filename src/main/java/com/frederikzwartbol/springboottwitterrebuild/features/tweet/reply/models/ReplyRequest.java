package com.frederikzwartbol.springboottwitterrebuild.features.tweet.reply.models;

public record ReplyRequest(Long tweetId, String message, Long userId) {
}
