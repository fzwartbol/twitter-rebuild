package com.example.springbootjpamanytomany.service;

import com.example.springbootjpamanytomany.entity.Like;
import com.example.springbootjpamanytomany.entity.Tweet;
import com.example.springbootjpamanytomany.repository.LikeRepository;
import com.example.springbootjpamanytomany.request.LikeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final TweetService tweetService;

    public Boolean addLike(LikeRequest request) {
        Tweet tweet = tweetService.findTweetById(request.getTweetId());
           Like like = new Like(request.getUserId(), LocalDateTime.now());
           like.getTweets().add(tweet);
           tweet.getLikes().add(like);
           likeRepository.save(like);
       return true;
    }

}
