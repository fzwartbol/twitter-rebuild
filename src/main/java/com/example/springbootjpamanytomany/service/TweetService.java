package com.example.springbootjpamanytomany.service;

import com.example.springbootjpamanytomany.repository.TweetRepository;
import com.example.springbootjpamanytomany.entity.Hashtag;
import com.example.springbootjpamanytomany.entity.Tweet;
import com.example.springbootjpamanytomany.exceptions.TweetNotFoundException;
import com.example.springbootjpamanytomany.request.TweetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetService {

    private final TweetRepository tweetRepository;
    private final HashtagService hashtagService;
    private final UserService userService;

    public List<Tweet> findAll() {
        return tweetRepository.findAll();
    }

    public Tweet saveTweet(TweetRequest request) {
        Hashtag hashTag = new Hashtag(request.getHashtag());
        Tweet tweet = new Tweet(
                request.getTitle(),
                request.getMessage(),
                LocalDateTime.now());
        tweet.setUser(userService.findUserById(request.getUserId()));
        tweet.getHashtags().add(hashTag);
        return tweetRepository.save(tweet);
    }

    public Tweet findTweetById(Long tweetId) {
        return tweetRepository.findById(tweetId)
                .orElseThrow(()-> new TweetNotFoundException("Tweet not found"));
    }

    public Tweet updateTweet(TweetRequest request) {
        Tweet tweet = new Tweet();
        return tweet;
    }

    public void deleteTweetById(Long tweetId) {
        tweetRepository.deleteById(tweetId);
    }

    public List<Tweet> findTweetsByHashtag(List<String> hashtag) {
        List<Tweet> tweetList = new ArrayList<>();
        hashtag.stream()
                .forEach(e ->  tweetList.addAll(tweetRepository.findAllByHashtags(e)));
        return  tweetList;
    }

    public Page<Tweet> findAllPaginated(Optional<Integer> page, Optional<String> sortBy) {
        return tweetRepository.findAll(
                PageRequest.of(
                page.orElse(0), 5,
                Sort.Direction.ASC, sortBy.orElse("id")
            )
        );
    }
}
