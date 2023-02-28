package com.frederikzwartbol.springboottwitterrebuild.features.conversation.models;

import java.util.Set;

public record StartConversationRequest(Set<Long> userIds) {
}
