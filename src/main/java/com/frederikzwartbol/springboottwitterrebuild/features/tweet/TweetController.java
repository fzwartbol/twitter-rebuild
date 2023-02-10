package com.frederikzwartbol.springboottwitterrebuild.features.tweet;

import com.frederikzwartbol.springboottwitterrebuild.features.tweet.models.dto.TweetDTO;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.models.dto.TweetMinimalDTO;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.models.dto.TweetRequest;
import com.frederikzwartbol.springboottwitterrebuild.util.mapper.TweetMapper;
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
    public ResponseEntity<List<TweetMinimalDTO>> findAllTweets() {
        return ResponseEntity.ok(tweetService.findAll()
                .stream()
                .map(tweet -> TweetMapper.createMinimalTweetDTO(tweet, tweetService.getMetaDataTweet(tweet)))
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<TweetDTO> saveTweet(TweetRequest request) {
        Tweet tweet = tweetService.saveTweet(request);
        return ResponseEntity.ok(
                TweetMapper.createDetailTweetDTO(tweet, tweetService.getMetaDataTweet(tweet)));
    }

    @Override
    public ResponseEntity<TweetDTO> findTweetById(Long tweetId) {
        var tweet =tweetService.findTweetById(tweetId);
        return ResponseEntity.ok(
                TweetMapper.createDetailTweetDTO(tweet
                        ,tweetService.getMetaDataTweet(tweet)));
    }

    @Override
    public ResponseEntity<TweetDTO> updateTweet(TweetRequest request) {
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
    public ResponseEntity<Page<TweetMinimalDTO>> findAllTweetsPaginated(Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(
                tweetService.findAllPaginated(page, sortBy)
                        .map(TweetMapper::createMinimalTweetDTO));
    }

    @Override
    public ResponseEntity<Page<TweetMinimalDTO>> findTweetsUser(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(
                tweetService.findTweetsByUserPaginated(userId, page, sortBy)
                        .map(TweetMapper::createMinimalTweetDTO));
    }

    @Override
    public ResponseEntity<Page<TweetMinimalDTO>> findTweetsWithRepliesUser(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(
                tweetService.findTweetsAndRepliesByUserPaginated(userId, page, sortBy)
                        .map(TweetMapper::createMinimalTweetDTO));
    }

    @Override
    public ResponseEntity<Page<TweetMinimalDTO>> findTweetsWithMediaUser(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(
                tweetService.findTweetsMediaByUserPaginated(userId, page, sortBy)
                        .map(TweetMapper::createMinimalTweetDTO));
    }

    @Override
    public ResponseEntity<Page<TweetMinimalDTO>> getLikedTweetsByUser(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(
                tweetService.findLikedTweetsByUserPaginated(userId, page, sortBy)
                        .map(TweetMapper::createMinimalTweetDTO));
    }

}
