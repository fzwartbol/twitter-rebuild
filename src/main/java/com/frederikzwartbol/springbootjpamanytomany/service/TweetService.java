package com.frederikzwartbol.springbootjpamanytomany.service;

import com.frederikzwartbol.springbootjpamanytomany.models.DTO.MetaTweetResponseDTO;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.Category;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.Tweet;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.user.User;
import com.frederikzwartbol.springbootjpamanytomany.repository.TweetRepository;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.Hashtag;
import com.frederikzwartbol.springbootjpamanytomany.exceptions.TweetNotFoundException;
import com.frederikzwartbol.springbootjpamanytomany.models.request.ReplyRequest;
import com.frederikzwartbol.springbootjpamanytomany.models.request.TweetRequest;
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
public class TweetService {

    private final TweetRepository tweetRepository;
    private final CategoryService categoryService;
    private final HashtagService hashtagService;
    private final UserService userService;

    /** CRUD function TWEET ENTITY */
    public List<Tweet> findAll() {
        return tweetRepository.findAll();

    }

    public Tweet saveTweet(TweetRequest request) {
        Tweet tweet = new Tweet(request.message(), LocalDateTime.now());
        tweet.setUser(userService.findUserById(request.userId()));
        List<Hashtag> hashtags = hashtagService.parseHashTagsFromRequest(request);
        hashtags.stream().forEach(hashtag -> hashtag.getTweets().add(tweet));
        tweet.getHashtags().addAll(hashtags);
        Category category = categoryService.saveCategory(request.category());
        category.getTweets().add(tweet);
        tweet.setCategory(category);
        return tweetRepository.save(tweet);
    }

    public Tweet updateTweet(TweetRequest request) {
        Tweet tweet =tweetRepository.findById(request.tweetId())
                .orElseThrow(()-> new TweetNotFoundException("Tweet not found"));
        tweet.setMessage(request.message());
        return tweetRepository.save(tweet);
    }
    public void deleteTweetById(Long tweetId) {
        tweetRepository.deleteById(tweetId);
    }

    /** Find Tweet Methods */
    public Tweet findTweetById(Long tweetId) {
        return tweetRepository.findById(tweetId)
                .orElseThrow(()-> new TweetNotFoundException("Tweet not found"));
    }

    public Page<Tweet> findTweetsSuscribedCategoryforUser(User user, Optional<Integer> page, Optional<String> sortBy) {
        return tweetRepository.findAllByCategoryIn(
                PageRequest.of(
                    page.orElse(0), 5,
                    Sort.by(Sort.Direction.DESC, sortBy.orElse("likeCount"))),user.getInterestedCategories());
    }

    public Page<Tweet> findAllPaginated(Optional<Integer> page, Optional<String> sortBy) {
        return tweetRepository.findAllByParentTweetIsNull(
                PageRequest.of(
                    page.orElse(0), 5,
                    Sort.by(Sort.Direction.DESC, sortBy.orElse("id")))
        );
    }

    public Page<Tweet> findTweetsByUserPaginated(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        return tweetRepository.findAllByParentTweetIsNullAndUser_Id(
                userId,
                PageRequest.of(
                        page.orElse(0), 5,
                        Sort.by(Sort.Direction.DESC, sortBy.orElse("id"))));
    }

    public Page<Tweet> findTweetsAndRepliesByUserPaginated(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        return tweetRepository.findAllByUser_Id(
                userId,
                PageRequest.of(
                        page.orElse(0), 5,
                        Sort.by(Sort.Direction.DESC, sortBy.orElse("id")))
        );
    }

    public Page<Tweet> findLikedTweetsByUserPaginated(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        return tweetRepository.findAllByLikes_userId(
                userId,
                PageRequest.of(
                        page.orElse(0), 5,
                        Sort.by(Sort.Direction.DESC, sortBy.orElse("id")))
        );
    }

    public Page<Tweet>  findTweetsMediaByUserPaginated(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        return tweetRepository.findAllByUser_IdAndMedia_idIsNotNull(
                userId,
                PageRequest.of(
                        page.orElse(0), 5,
                        Sort.by(Sort.Direction.DESC, sortBy.orElse("id")))
        );
    }

    public Tweet saveReplyToTweet(Long id, ReplyRequest request) {
        Tweet parentTweet = tweetRepository.findById(id).orElseThrow(()-> new TweetNotFoundException("Tweet not found"));
        Tweet replyTweet = new Tweet();
        replyTweet.setUser(userService.findUserById(request.userId()));
        replyTweet.setMessage(request.message());
        replyTweet.setPublicationDate(LocalDateTime.now());
        replyTweet.setParentTweet(parentTweet);
        parentTweet.getReplies().add(replyTweet);
        return tweetRepository.saveAndFlush(replyTweet);
    }

    public  MetaTweetResponseDTO getMetaDataTweet(Tweet tweet) {
        return new MetaTweetResponseDTO(tweetRepository.tweetLikedByUser(tweet.getId(), 1L),
                tweetRepository.tweetRepliedByUser(tweet.getId(), 1L));
    }



}
