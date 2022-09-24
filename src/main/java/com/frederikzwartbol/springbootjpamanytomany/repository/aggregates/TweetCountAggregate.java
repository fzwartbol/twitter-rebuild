package com.frederikzwartbol.springbootjpamanytomany.repository.aggregates;


public interface TweetCountAggregate {
    String getCount();
    Long getTweetId();
}
