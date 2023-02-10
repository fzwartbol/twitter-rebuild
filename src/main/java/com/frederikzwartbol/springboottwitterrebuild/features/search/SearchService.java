package com.frederikzwartbol.springboottwitterrebuild.features.search;

import com.frederikzwartbol.springboottwitterrebuild.features.tweet.hashtag.Hashtag;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.models.children.MediaTypeFormat;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.Tweet;
import com.frederikzwartbol.springboottwitterrebuild.features.user.User;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.TweetRepository;
import com.frederikzwartbol.springboottwitterrebuild.features.user.UserRepository;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.hashtag.HashtagService;
import com.frederikzwartbol.springboottwitterrebuild.util.mapper.TweetMapper;
import com.frederikzwartbol.springboottwitterrebuild.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {

    private final UserRepository userRepository;
    private final HashtagService hashtagService;
    private final TweetRepository tweetRepository;

    public SearchAggregate returnSearchQuery(String q, String f, String src, Optional<Integer> page, Optional<String> sortBy) {
        QUERY_FIELD_OPTIONS queryFieldOptions = QUERY_FIELD_OPTIONS.valueOf(f.toUpperCase());
        return switch (queryFieldOptions) {
            case LIVE -> searchTweetsLatest(q, f, src, page, sortBy);
            case USER -> searchUsers(q, f, src, page, sortBy);
            case IMAGE, VIDEO -> searchTweetsMedia(q, f, src, page, sortBy);
            case TWEET -> searchTweets(q, f, src, page, sortBy);
        };
    }

    public SearchAggregate searchTweets(String q, String f, String src, Optional<Integer> page, Optional<String> sortBy) {
        var searchAggregate = buildSearchAggregate(q, f, src, page, sortBy);
        searchAggregate.setQueryTweets(
                searchTweetsByHashtag(q, f, page, sortBy)
                        .map(TweetMapper::createMinimalTweetDTO));
        return searchAggregate;
    }

    public SearchAggregate searchTweetsLatest(String q, String f, String src, Optional<Integer> page, Optional<String> sortBy) {
        sortBy = Optional.of("id");
        var searchAggregate = buildSearchAggregate(q, f, src, page, sortBy);
        searchAggregate.setQueryTweets(searchTweetsByHashtag(q, f, page, sortBy).map(TweetMapper::createMinimalTweetDTO));
        return searchAggregate;
    }

    public SearchAggregate searchTweetsMedia(String q, String f, String src, Optional<Integer> page, Optional<String> sortBy) {
        var searchAggregate = buildSearchAggregate(q, f, src, page, sortBy);
        searchAggregate.setQueryTweets(
                searchTweetsByHashtagAndMedia(q, f, page, sortBy)
                        .map(TweetMapper::createMinimalTweetDTO));
        return searchAggregate;
    }

    public SearchAggregate searchUsers(String q, String f, String src, Optional<Integer> page, Optional<String> sortBy) {
        var searchAggregate = buildSearchAggregate(q, f, src, page, sortBy);
        searchAggregate.setQueryUsers(searchUsersByName(q, page, sortBy)
                .map(UserMapper::entityToMinimalDTO));
        return searchAggregate;
    }

    private Page<Tweet> searchTweetsByHashtag(String query, String f, Optional<Integer> page, Optional<String> sortBy) {
        List<Hashtag> hashtagList = hashtagService.findHashtagsByMessage(List.of(query.split(" ")));
        return tweetRepository.findAllByHashtagsIn(hashtagList,PageRequest.of(
                page.orElse(0), 5,
                Sort.by(Sort.Direction.DESC, sortBy.orElse("likeCount"))));
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

    public enum QUERY_FIELD_OPTIONS {
        TWEET, LIVE, USER, IMAGE, VIDEO
    }

}

