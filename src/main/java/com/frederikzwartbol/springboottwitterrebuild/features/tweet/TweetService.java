package com.frederikzwartbol.springboottwitterrebuild.features.tweet;

import com.frederikzwartbol.springboottwitterrebuild.exceptions.TweetNotFoundException;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.models.dto.MetaTweetResponseDTO;
import com.frederikzwartbol.springboottwitterrebuild.features.category.CategoryService;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.hashtag.HashtagService;
import com.frederikzwartbol.springboottwitterrebuild.features.user.User;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.reply.models.ReplyRequest;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.models.dto.TweetRequest;
import com.frederikzwartbol.springboottwitterrebuild.features.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.frederikzwartbol.springboottwitterrebuild.features.authentication.SecurityConstants.ANONYMOUS_AUTH_USER;

@Service
@RequiredArgsConstructor
@Slf4j
public class TweetService {
    private final TweetRepository tweetRepository;
    private final CategoryService categoryService;
    private final HashtagService hashtagService;
    private final UserService userService;

    public List<Tweet> findAll() {return tweetRepository.findAll();}

    public Tweet saveTweet(TweetRequest request) {
        var tweet = Tweet.builder()
                .message(request.message())
                .user(userService.findUserById(request.userId()))
                .publicationDate(LocalDateTime.now())
                .hashtags(hashtagService.parseHashTagsFromRequest(request))
                .category(categoryService.saveCategory(request.category()))
                .build();
        return tweetRepository.save(tweet);
    }

    public Tweet updateTweet(TweetRequest request) {
        Tweet tweet = tweetRepository.findById(request.tweetId())
                .orElseThrow(() -> new TweetNotFoundException("Tweet not found"));
        tweet.setMessage(request.message());
        return tweetRepository.save(tweet);
    }

    public void deleteTweetById(Long tweetId) {
        tweetRepository.deleteById(tweetId);
    }

    public Tweet findTweetById(Long tweetId) {
        return tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException("Tweet not found"));
    }

    public Tweet saveReplyToTweet(Long id, ReplyRequest request) {
        var parentTweet = tweetRepository.findById(id).orElseThrow(() -> new TweetNotFoundException("Tweet not found"));
        var replyTweet = Tweet.builder()
                .user(userService.findUserById(request.userId()))
                .message(request.message())
                .publicationDate(LocalDateTime.now())
                .parentTweet(parentTweet)
                .build();
        parentTweet.getReplies().add(replyTweet);
        return tweetRepository.save(replyTweet);
    }

    public Page<Tweet> findTweetsSuscribedCategoryforUser(User user, Optional<Integer> page, Optional<String> sortBy) {
        return tweetRepository.findAllByCategoryIn(user.getInterestedCategories(),
                PageRequest.of(
                        page.orElse(0), 5,
                        Sort.by(Sort.Direction.DESC, sortBy.orElse("likeCount"))));
    }

    public Page<Tweet> findAllPaginated(Optional<Integer> page, Optional<String> sortBy) {
        return tweetRepository.findAllByParentTweetIsNull(
                PageRequest.of(
                        page.orElse(0), 5,
                        Sort.by(Sort.Direction.DESC, sortBy.orElse("id")))
        );
    }

    public Page<Tweet> findTweetsByUserPaginated(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        log.debug("Finding tweets by user with userId {}",userId);
        return tweetRepository.findAllByParentTweetIsNullAndUser_Id(
                userId,
                PageRequest.of(
                        page.orElse(0), 5,
                        Sort.by(Sort.Direction.DESC, sortBy.orElse("id"))));
    }

    public Page<Tweet> findTweetsAndRepliesByUserPaginated(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        log.debug("Finding tweets and replies by user with userId {}",userId);
        return tweetRepository.findAllByUser_Id(
                userId,
                PageRequest.of(
                        page.orElse(0), 5,
                        Sort.by(Sort.Direction.DESC, sortBy.orElse("id")))
        );
    }

    public Page<Tweet> findLikedTweetsByUserPaginated(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        log.debug("Finding liked tweets by user with userId {}",userId);
        return tweetRepository.findAllByLikes_userId(
                userId,
                PageRequest.of(
                        page.orElse(0), 5,
                        Sort.by(Sort.Direction.DESC, sortBy.orElse("id")))
        );
    }

    public Page<Tweet> findTweetsMediaByUserPaginated(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        log.debug("Finding tweets with media by user with userId {}",userId);
        return tweetRepository.findAllByUser_IdAndMedia_idIsNotNull(
                userId,
                PageRequest.of(
                        page.orElse(0), 5,
                        Sort.by(Sort.Direction.DESC, sortBy.orElse("id")))
        );
    }

    public MetaTweetResponseDTO getMetaDataTweet(Tweet tweet) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated() && !authentication.getName().equals(ANONYMOUS_AUTH_USER)) {
            var currentUser = userService.findUserByEmailAddress(authentication.getName());

            log.debug("Retrieving user metadata of user {} about tweet", currentUser.getCredentials().getUsername());

            return new MetaTweetResponseDTO(tweetRepository.tweetLikedByUser(tweet.getId(), currentUser.getId()),
                    tweetRepository.tweetRepliedByUser(tweet.getId(), currentUser.getId()));
        }
        return  new MetaTweetResponseDTO(null,null);
    }
}
