package com.frederikzwartbol.springboottwitterrebuild.features.trending.aggregates;


public interface TweetCountAggregate {
    String getCount();

    Long getTweetId();
}
