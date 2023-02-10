package com.frederikzwartbol.springboottwitterrebuild.features.user.feed;

import com.frederikzwartbol.springboottwitterrebuild.aspect.UserScope;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.Tweet;
import com.frederikzwartbol.springboottwitterrebuild.features.user.User;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.TweetRepository;
import com.frederikzwartbol.springboottwitterrebuild.features.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The FeedService class is responsible for providing the feed of tweets to a user.
 *
 * It uses two repositories, TweetRepository and UserRepository, to retrieve the necessary information.
 *
 * The getFeedForUser method takes a username and optional page number and sorting criteria and returns
 * a slice of tweets that are relevant to the user's interests and people they follow.
 *
 * The getFeed method is a helper method that returns a slice of tweets based on the user's interests and people they follow.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FeedService {
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @UserScope
    public Slice<Tweet> findFeedForUser(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        Optional<User> optUser = userRepository.findById(userId);
        var pageable = PageRequest.of(
                page.orElse(0), 10,
                Sort.by(Sort.Direction.DESC, sortBy.orElse("publicationDate")));
        return optUser.map( user -> getFeedForUser(user, pageable)).orElse(Page.empty());
    }

    public  Slice<Tweet> getFeedForUser(User user, Pageable pageable) {
        log.debug("Retrieving feed for user: {}", user.getCredentials().getUsername());
        var tweets = tweetRepository.findTweetByCategoryInOrUser_FollowingIn(
                user.getInterestedCategories(),
                user.getFollowing(),
                pageable);
        return tweets;
    }
}
