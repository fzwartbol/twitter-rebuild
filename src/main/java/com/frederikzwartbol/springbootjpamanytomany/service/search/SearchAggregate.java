package com.frederikzwartbol.springbootjpamanytomany.service.search;

import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.Tweet;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.user.User;
import org.springframework.data.domain.Page;

public class SearchAggregate {

    Page<Tweet> queryTweets;
    Page<User>  queryUsers;
}
