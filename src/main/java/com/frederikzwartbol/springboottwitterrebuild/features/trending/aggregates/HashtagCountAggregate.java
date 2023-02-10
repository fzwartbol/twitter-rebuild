package com.frederikzwartbol.springboottwitterrebuild.features.trending.aggregates;

public interface HashtagCountAggregate {
    Long getHashtagId();

    String getHashtagCount();

    String getHashtagMessage();
}
