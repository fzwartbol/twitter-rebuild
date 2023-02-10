package com.frederikzwartbol.springboottwitterrebuild.features.tweet.hashtag;

import com.frederikzwartbol.springboottwitterrebuild.features.trending.aggregates.HashtagCountAggregate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HashTagRepository extends JpaRepository<Hashtag, Long> {
    List<Hashtag> findHashtagsByMessageContaining(String message);

    List<Hashtag> findAllByMessageIgnoreCaseIn(List<String> message);

    Optional<Hashtag> findHashtagByMessage(String message);

    @Query(value = "SELECT h.id as hashtagId, h.message as hashtagMessage," +
            "h.tweets.size as hashtagCount FROM Hashtag h ORDER BY hashtagCount DESC ")
    List<HashtagCountAggregate> getHashtagMentions();
}
