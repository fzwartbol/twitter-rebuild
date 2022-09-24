package com.frederikzwartbol.springbootjpamanytomany.models.request;

public record ReplyRequest(Long tweetId, String message, Long userId) {
}
