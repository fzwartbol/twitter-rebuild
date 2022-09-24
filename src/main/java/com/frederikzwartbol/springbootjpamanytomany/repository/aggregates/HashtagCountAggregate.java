package com.frederikzwartbol.springbootjpamanytomany.repository.aggregates;

public interface HashtagCountAggregate {
    Long getHashtagId();
    String getHashtagCount();
    String getHashtagMessage();
}
