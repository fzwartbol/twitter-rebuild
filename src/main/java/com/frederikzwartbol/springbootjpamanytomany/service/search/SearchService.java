package com.frederikzwartbol.springbootjpamanytomany.service.search;

import com.frederikzwartbol.springbootjpamanytomany.models.entity.Hashtag;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.MediaTypeFormat;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.Tweet;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.user.User;
import com.frederikzwartbol.springbootjpamanytomany.repository.TweetRepository;
import com.frederikzwartbol.springbootjpamanytomany.repository.UserRepository;
import com.frederikzwartbol.springbootjpamanytomany.service.HashtagService;
import com.frederikzwartbol.springbootjpamanytomany.util.mapper.TweetMapper;
import com.frederikzwartbol.springbootjpamanytomany.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {

    private final UserRepository userRepository;
    private final HashtagService hashtagService;
    private final TweetRepository tweetRepository;

    // hashtags in tweets, most liked
    // hashtags in tweets latest
    // person
    // hastag in tweets with image, most liked
    // hasthag in tweets with video, most liked
    public enum QUERY_FIELD_OPTIONS  {
        TWEET, LIVE, USER, IMAGE, VIDEO
    }

    enum SRC_OPTIONS {
        TYPED_QUERY
    }

    /** Example of Twitter api query
     * https://twitter.com/search?q=hallo&src=typed_query&f=user
     * @param q
     * @param f
     * @param src
     * @param page
     * @param sortBy
     * @return
     */
    public SearchAggregate returnSearchQuery(String q, String f, String src, Optional<Integer> page, Optional<String> sortBy) {
        QUERY_FIELD_OPTIONS queryFieldOptions = QUERY_FIELD_OPTIONS.valueOf(f.toUpperCase());
        return switch (queryFieldOptions) {
            case LIVE -> searchTweetsLive(q, f, src, page, sortBy);
            case USER -> searchUsers(q, f, src, page, sortBy);
            case IMAGE, VIDEO -> searchTweetsMedia(q, f, src, page, sortBy);
            case TWEET -> searchTweets(q, f, src, page, sortBy);
        };
    }

    public SearchAggregate searchTweets(String q, String f, String src, Optional<Integer> page, Optional<String> sortBy) {
        var searchAggregate = buildSearchAggregate(q, f, src, page, sortBy);
            searchAggregate.setQueryTweets(
                    searchTweetsByHashtag(q,f,page,sortBy)
                            .map(TweetMapper::createMinimalTweetDTO));
            return searchAggregate;
    }

    public SearchAggregate searchTweetsLive(String q, String f, String src, Optional<Integer> page, Optional<String> sortBy) {
        sortBy = Optional.of("id");
        var searchAggregate = buildSearchAggregate(q, f, src, page, sortBy);
        searchAggregate.setQueryTweets(searchTweetsByHashtag(q,f,page,sortBy).map(TweetMapper::createMinimalTweetDTO));
        return searchAggregate;
    }

    public SearchAggregate searchTweetsMedia(String q, String f, String src, Optional<Integer> page, Optional<String> sortBy) {
        var searchAggregate = buildSearchAggregate(q, f, src, page, sortBy);
        searchAggregate.setQueryTweets(
                searchTweetsByHashtagAndMedia(q,f,page,sortBy)
                        .map(TweetMapper::createMinimalTweetDTO));
        return searchAggregate;
    }

    public SearchAggregate searchUsers(String q, String f, String src, Optional<Integer> page, Optional<String> sortBy) {
        var searchAggregate = buildSearchAggregate(q, f, src, page, sortBy);
        searchAggregate.setQueryUsers(searchUsersByName(q,page,sortBy).map(UserMapper::entityToMinimalDTO));
        return searchAggregate;
    }

    private Page<Tweet> searchTweetsByHashtag(String query, String f, Optional<Integer> page, Optional<String> sortBy) {
        List<Hashtag> hashtagList = hashtagService.findHashtagsByMessage(List.of(query.split(" ")));
         return tweetRepository.findAllByHashtagsIn(PageRequest.of(
                page.orElse(0), 5,
                Sort.by(Sort.Direction.DESC, sortBy.orElse("likeCount"))),hashtagList);
    }
    private Page<Tweet> searchTweetsByHashtagAndMedia(String query, String f, Optional<Integer> page, Optional<String> sortBy) {
        List<Hashtag> hashtagList = hashtagService.findHashtagsByMessage(List.of(query.split(" ")));
        MediaTypeFormat type = MediaTypeFormat.valueOf(f.toUpperCase());
        return tweetRepository.findAllByHashtagsInAndMedia_Type(hashtagList, type, PageRequest.of(
                page.orElse(0), 5,
                Sort.by(Sort.Direction.DESC, sortBy.orElse("likeCount"))));
    }


    public Page<User> searchUsersByName(String query, Optional<Integer> page, Optional<String> sortBy) {
            return userRepository.findUserByTwitterHandleContaining(query,
                    PageRequest.of(
                    page.orElse(0), 5,
                    Sort.by(Sort.Direction.DESC, sortBy.orElse("followers"))));
    }

    private SearchAggregate buildSearchAggregate(String q, String f, String src, Optional<Integer> page, Optional<String> sortBy) {
        return new SearchAggregate.SearchAggregateBuilder()
                .query(q)
                .fieldType(f)
                .src(src)
                .page(page.orElse(0))
                .sortBy(sortBy.orElse(""))
                .timestamp(LocalDateTime.now())
                .build();
    }

}

