package com.example.springbootjpamanytomany.service;

import com.example.springbootjpamanytomany.repository.HashTagRepository;
import com.example.springbootjpamanytomany.entity.Hashtag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class HashtagService {

    private final HashTagRepository hashTagRepository;

    public Hashtag saveHashtagByName(String hashtagName) {
        Hashtag hashTag = new Hashtag(hashtagName);
//        hashTag.setCreatedAt(LocalDateTime.now());
       return hashTagRepository.save(hashTag);
    }

    public Hashtag saveHashtag(Hashtag hashTag) {
//        hashTag.setCreatedAt(LocalDateTime.now());
        return hashTagRepository.save(hashTag);
    }

    public Hashtag findHashtagByName (String hashtagName) {
//        return hashTagRepository.findByHashtagName(hashtagName);
        return new Hashtag();
    }

}
