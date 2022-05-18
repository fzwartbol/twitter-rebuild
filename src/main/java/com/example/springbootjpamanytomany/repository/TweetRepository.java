package com.example.springbootjpamanytomany.repository;

import com.example.springbootjpamanytomany.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet,Long> {
    List<Tweet> findAllByHashtags (String Hashtag);
}
