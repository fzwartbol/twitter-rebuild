package com.frederikzwartbol.springbootjpamanytomany.service.search;

import com.frederikzwartbol.springbootjpamanytomany.models.entity.Hashtag;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.Tweet;
import com.frederikzwartbol.springbootjpamanytomany.repository.TweetRepository;
import com.frederikzwartbol.springbootjpamanytomany.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SearchService {

    public final static Set<String> SRC_OPTIONS = Set.of("typed_query");
//  https://twitter.com/search?q=hallo&src=typed_query&f=user

    private final HashtagService hashtagService;
    private final TweetRepository tweetRepository;


    public SearchAggregate returnSearchQuery(String[] query, String f, String src, Optional<Integer> page, Optional<String> sortBy) {
//        String fieldType = Arrays.stream(QUERY_FIELD_OPTIONS.values()) ? f  :  "tweet" ;
        String srcType = SRC_OPTIONS.contains(src) ? f  :  "typed_query" ;

     return  new SearchAggregate();
    }

    public Page<Tweet> searchTweetsByHashtag(List<String> hashtags, Optional<Integer> page, Optional<String> sortBy) {
        List<Hashtag> hashtagList = hashtagService.findHashtagsByMessage(hashtags);
        return tweetRepository.findAllByHashtagsIn(PageRequest.of(
                page.orElse(0), 5,
                Sort.by(Sort.Direction.DESC, sortBy.orElse("id"))),hashtagList);
    }


    enum QUERY_FIELD_OPTIONS  {
        TWEET,
        USER,
        IMAGE,
        VIDEO,
        LIVE
    }

    enum SRC_OPTIONS {
        TYPED_QUERY
    }

}

