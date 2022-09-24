package com.frederikzwartbol.springbootjpamanytomany.controller.tweet;

import com.frederikzwartbol.springbootjpamanytomany.controller.tweet.operations.TweetOperations;
import com.frederikzwartbol.springbootjpamanytomany.models.DTO.TweetDetailResponseDTO;
import com.frederikzwartbol.springbootjpamanytomany.models.DTO.TweetMinimalResponseDTO;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.Tweet;
import com.frederikzwartbol.springbootjpamanytomany.models.request.TweetRequest;
import com.frederikzwartbol.springbootjpamanytomany.service.TweetService;
import com.frederikzwartbol.springbootjpamanytomany.util.mapper.TweetMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@Slf4j
public class TweetController implements TweetOperations {

    private final TweetService tweetService;

    @Override
    public ResponseEntity<List<TweetMinimalResponseDTO>> findAllTweets() {
        return ResponseEntity.ok(tweetService.findAll().stream()
                .map(tweet -> TweetMapper.createMinimalTweetDTO(tweet,tweetService.getMetaDataTweet(tweet)))
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<TweetDetailResponseDTO> saveTweet(TweetRequest request) {
        Tweet tweet = tweetService.saveTweet(request);
        return ResponseEntity.ok(
                TweetMapper.createDetailTweetDTO(tweet, tweetService.getMetaDataTweet(tweet)));
    }

    @Override
    public ResponseEntity<TweetDetailResponseDTO> findTweetById(Long tweetId) {
        return ResponseEntity.ok(
                TweetMapper.createDetailTweetDTO(
                tweetService.findTweetById(tweetId)));
    }

    @Override
    public ResponseEntity<TweetDetailResponseDTO> updateTweet(TweetRequest request) {
        return ResponseEntity.ok(
                TweetMapper.createDetailTweetDTO(
                tweetService.updateTweet(request)));
    }

    @Override
    public ResponseEntity<?> deleteTweetById(Long tweetId) {
        tweetService.deleteTweetById(tweetId);
        return ResponseEntity.ok("Tweet deleted");
    }


    @Override
    public ResponseEntity<Page<TweetMinimalResponseDTO>> findAllTweetsPaginated(Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(
                tweetService.findAllPaginated(page,sortBy)
                .map(TweetMapper::createMinimalTweetDTO));
    }

    @Override
    public ResponseEntity<Page<TweetMinimalResponseDTO>> findTweetsUser(Long userId,Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(
                tweetService.findTweetsByUserPaginated(userId, page,sortBy)
                .map(TweetMapper::createMinimalTweetDTO));
    }

    @Override
    public ResponseEntity<Page<TweetMinimalResponseDTO>> findTweetsWithRepliesUser(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(
                tweetService.findTweetsAndRepliesByUserPaginated(userId, page,sortBy)
                .map(TweetMapper::createMinimalTweetDTO));
    }

    @Override
    public ResponseEntity<Page<TweetMinimalResponseDTO>> findTweetsWithMediaUser(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(
                tweetService.findTweetsMediaByUserPaginated(userId, page,sortBy)
                .map(TweetMapper::createMinimalTweetDTO));
    }

    @Override
    public ResponseEntity<Page<TweetMinimalResponseDTO>> getLikedTweetsByUser(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(
                tweetService.findLikedTweetsByUserPaginated(userId, page,sortBy)
                .map(TweetMapper::createMinimalTweetDTO));
    }

}
