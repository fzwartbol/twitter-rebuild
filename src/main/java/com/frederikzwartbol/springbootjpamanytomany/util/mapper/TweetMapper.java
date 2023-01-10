package com.frederikzwartbol.springbootjpamanytomany.util.mapper;

import com.frederikzwartbol.springbootjpamanytomany.models.DTO.MetaTweetResponseDTO;
import com.frederikzwartbol.springbootjpamanytomany.models.DTO.tweet.TweetMinimalDTO;
import com.frederikzwartbol.springbootjpamanytomany.models.DTO.tweet.TweetDTO;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.Tweet;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class TweetMapper {
    public static TweetMinimalDTO createMinimalTweetDTO(Tweet tweet) {
        TweetMinimalDTO dto = new TweetMinimalDTO();
        BeanUtils.copyProperties( tweet, dto);
        Optional.ofNullable(tweet.getParentTweet())
                .ifPresent(optionalTweet -> dto.setParentTweet(createMinimalTweetDTO(optionalTweet)));
        dto.setCategory(tweet.getCategory());
//        dto.setMetaData(new MetaTweetResponseDTO(tweetRepository.tweetLikedByUser(tweet.getId(), 1L),tweetRepository.tweetRepliedByUser(tweet.getId(), 1L)));
        return dto;
    }

    public static TweetMinimalDTO createMinimalTweetDTO(Tweet tweet, MetaTweetResponseDTO metaData) {
        TweetMinimalDTO dto = new TweetMinimalDTO();
        BeanUtils.copyProperties( tweet, dto);
        Optional.ofNullable(tweet.getParentTweet())
                .ifPresent(optionalTweet -> dto.setParentTweet(createMinimalTweetDTO(optionalTweet)));
        dto.setCategory(tweet.getCategory());
        dto.setMetaData(metaData);
        return dto;
    }


    public static TweetDTO createDetailTweetDTO(Tweet tweet) {
        TweetDTO dto = new TweetDTO();
        BeanUtils.copyProperties( tweet, dto);
        Optional.ofNullable(tweet.getParentTweet())
                .ifPresent(optionalTweet -> dto.setParentTweet(createMinimalTweetDTO(optionalTweet)));
        dto.setReplies(tweet.getReplies().stream().map(TweetMapper::createDetailTweetDTO).collect(Collectors.toSet()));
        dto.setLikeCount(dto.getLikes().size());
        dto.setReplyCount(dto.getReplies().size());
        dto.setHashtags(tweet.getHashtags());
        dto.setCategory(tweet.getCategory());
        return dto;
    }

    public static TweetDTO createDetailTweetDTO(Tweet tweet, MetaTweetResponseDTO metaData) {
        TweetDTO dto = new TweetDTO();
        BeanUtils.copyProperties( tweet, dto);
        Optional.ofNullable(tweet.getParentTweet())
                .ifPresent(optionalTweet -> dto.setParentTweet(createMinimalTweetDTO(optionalTweet)));
        dto.setReplies(tweet.getReplies().stream().map(TweetMapper::createDetailTweetDTO).collect(Collectors.toSet()));
        dto.setLikeCount(dto.getLikes().size());
        dto.setReplyCount(dto.getReplies().size());
        dto.setHashtags(tweet.getHashtags());
        dto.setCategory(tweet.getCategory());
        dto.setMetaData(metaData);
        return dto;
    }
}
