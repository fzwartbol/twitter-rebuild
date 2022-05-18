package com.example.springbootjpamanytomany;

import com.example.springbootjpamanytomany.enitity.Hashtag;
import com.example.springbootjpamanytomany.enitity.Tweet;
import com.example.springbootjpamanytomany.enitity.User;
import com.example.springbootjpamanytomany.repository.HashTagRepository;
import com.example.springbootjpamanytomany.repository.TweetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class SpringbootJpaManytomanyApplication implements CommandLineRunner {


    private TweetRepository tweetRepository;
    private HashTagRepository hashTagRepository;

    public SpringbootJpaManytomanyApplication(TweetRepository tweetRepository, HashTagRepository hashTagRepository) {
        this.tweetRepository = tweetRepository;
        this.hashTagRepository = hashTagRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaManytomanyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("frederik","zwartbol","frederikzwartbol@gmail.com");
        Tweet tweet = new Tweet("post 1","hallo", LocalDateTime.now());
        Tweet tweet2 = new Tweet("post 2","hallo", LocalDateTime.now());

        Hashtag hashtag = new Hashtag("tags1");
        Hashtag hashtag2 = new Hashtag("tags2");
        tweet.getHashtags().add(hashtag);
        tweet.getHashtags().add(hashtag2);
        tweet.setUser(user);

        this.tweetRepository.save(tweet);
    }


    public TweetRepository getPostRepository() {
        return tweetRepository;
    }

    public void setPostRepository(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public HashTagRepository getTagRepository() {
        return hashTagRepository;
    }

    public void setTagRepository(HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }
}
