package com.frederikzwartbol.springbootjpamanytomany.repository;

import com.frederikzwartbol.springbootjpamanytomany.models.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    Optional<Like> findByTweetIdAndUserIdAndActiveTrue (Long tweetId, Long userId);
}
