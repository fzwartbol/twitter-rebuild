package com.frederikzwartbol.springbootjpamanytomany.service;

import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.Tweet;
import com.frederikzwartbol.springbootjpamanytomany.repository.TweetRepository;
import com.frederikzwartbol.springbootjpamanytomany.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final TweetRepository repository;
    private final UserRepository userRepository;

    public Page<Tweet> getFeedForUser() {
        return repository.findTweetsForFollowing( userRepository.getById(1L), PageRequest.of(
                        0, 5));
    }
}
