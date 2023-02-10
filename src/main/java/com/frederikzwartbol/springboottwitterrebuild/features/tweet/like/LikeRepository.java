package com.frederikzwartbol.springboottwitterrebuild.features.tweet.like;

import com.frederikzwartbol.springboottwitterrebuild.features.tweet.like.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByTweetIdAndUserIdAndActiveTrue(Long tweetId, Long userId);
}
