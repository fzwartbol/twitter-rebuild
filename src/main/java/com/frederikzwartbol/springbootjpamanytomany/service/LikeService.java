package com.frederikzwartbol.springbootjpamanytomany.service;

import com.frederikzwartbol.springbootjpamanytomany.models.DTO.LikeResponseDTO;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.Like;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.Tweet;
import com.frederikzwartbol.springbootjpamanytomany.exceptions.TweetNotFoundException;
import com.frederikzwartbol.springbootjpamanytomany.repository.LikeRepository;
import com.frederikzwartbol.springbootjpamanytomany.repository.TweetRepository;
import com.frederikzwartbol.springbootjpamanytomany.models.request.LikeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final TweetRepository tweetRepository;

    /**
     *
     * @param request
     * @return a responseDTO
     */
    public LikeResponseDTO checkLike(LikeRequest request) {
        Optional<Like> optionalLike = likeRepository.findByTweetIdAndUserIdAndActiveTrue(request.tweetId(), request.userId());
        Tweet tweet = tweetRepository.findById(
                request.tweetId()).orElseThrow(() -> new TweetNotFoundException("Tweet not found"));
        if (optionalLike.isEmpty()) {
            return addLike(tweet, request);
        } else {
            return removeLike(tweet, optionalLike.get());
        }
    }

    public LikeResponseDTO addLike(Tweet tweet,LikeRequest request) {
            Like like = new Like(request.userId(), LocalDateTime.now());
            like.setTweet(tweet);
            tweet.getLikes().add(like);
            likeRepository.save(like);
            tweetRepository.save(tweet);
            return  new LikeResponseDTO(tweet.getUser().getId(),tweet.getLikes().size(),true);
        }

    public LikeResponseDTO removeLike(Tweet tweet,Like like) {
        like.setActive(false);
        tweetRepository.save(tweet);
        return  new LikeResponseDTO(tweet.getUser().getId(),tweet.getLikes().size(),false);
    }

}
