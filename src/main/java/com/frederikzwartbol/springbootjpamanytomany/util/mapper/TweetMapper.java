package com.frederikzwartbol.springbootjpamanytomany.util.mapper;

import com.frederikzwartbol.springbootjpamanytomany.models.DTO.MetaTweetResponseDTO;
import com.frederikzwartbol.springbootjpamanytomany.models.DTO.TweetMinimalResponseDTO;
import com.frederikzwartbol.springbootjpamanytomany.models.DTO.TweetDetailResponseDTO;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.Tweet;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class TweetMapper {
    public static TweetMinimalResponseDTO createMinimalTweetDTO(Tweet tweet) {
        TweetMinimalResponseDTO dto = new TweetMinimalResponseDTO();
        BeanUtils.copyProperties( tweet, dto);
        Optional.ofNullable(tweet.getParentTweet())
                .ifPresent(optionalTweet -> dto.setParentTweet(createMinimalTweetDTO(optionalTweet)));
        dto.setCategory(tweet.getCategory());
//        dto.setMetaData(new MetaTweetResponseDTO(tweetRepository.tweetLikedByUser(tweet.getId(), 1L),tweetRepository.tweetRepliedByUser(tweet.getId(), 1L)));
        return dto;
    }

    public static TweetMinimalResponseDTO createMinimalTweetDTO(Tweet tweet, MetaTweetResponseDTO metaData) {
        TweetMinimalResponseDTO dto = new TweetMinimalResponseDTO();
        BeanUtils.copyProperties( tweet, dto);
        Optional.ofNullable(tweet.getParentTweet())
                .ifPresent(optionalTweet -> dto.setParentTweet(createMinimalTweetDTO(optionalTweet)));
        dto.setCategory(tweet.getCategory());
        dto.setMetaData(metaData);
        return dto;
    }


    public static TweetDetailResponseDTO createDetailTweetDTO(Tweet tweet) {
        TweetDetailResponseDTO dto = new TweetDetailResponseDTO();
        BeanUtils.copyProperties( tweet, dto);
//        log.info("tweet detail parent:{}",tweet.getParentTweet());
        Optional.ofNullable(tweet.getParentTweet())
                .ifPresent(optionalTweet -> dto.setParentTweet(createMinimalTweetDTO(optionalTweet)));
        dto.setReplies(tweet.getReplies().stream().map(TweetMapper::createDetailTweetDTO).collect(Collectors.toSet()));
        dto.setLikeCount(dto.getLikes().size());
        dto.setReplyCount(dto.getReplies().size());
        dto.setHashtags(tweet.getHashtags());
        dto.setCategory(tweet.getCategory());
        return dto;
    }

    public static TweetDetailResponseDTO createDetailTweetDTO(Tweet tweet, MetaTweetResponseDTO metaData) {
        TweetDetailResponseDTO dto = new TweetDetailResponseDTO();
        BeanUtils.copyProperties( tweet, dto);
//        log.info("tweet detail parent:{}",tweet.getParentTweet());
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
