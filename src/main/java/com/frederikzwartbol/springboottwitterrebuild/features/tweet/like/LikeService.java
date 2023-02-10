package com.frederikzwartbol.springboottwitterrebuild.features.tweet.like;

import com.frederikzwartbol.springboottwitterrebuild.features.tweet.like.model.LikeRequest;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.like.model.LikeResponseDTO;
import com.frederikzwartbol.springboottwitterrebuild.exceptions.TweetNotFoundException;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.like.model.Like;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.Tweet;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.TweetRepository;
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

    public LikeResponseDTO addLike(Tweet tweet, LikeRequest request) {
        Like like = new Like(request.userId(), LocalDateTime.now());
        like.setTweet(tweet);
        tweet.getLikes().add(like);
        likeRepository.save(like);
        tweetRepository.save(tweet);
        return new LikeResponseDTO(tweet.getUser().getId(), tweet.getLikes().size(), true);
    }

    public LikeResponseDTO removeLike(Tweet tweet, Like like) {
        like.setActive(false);
        tweetRepository.save(tweet);
        return new LikeResponseDTO(tweet.getUser().getId(), tweet.getLikes().size(), false);
    }

}
