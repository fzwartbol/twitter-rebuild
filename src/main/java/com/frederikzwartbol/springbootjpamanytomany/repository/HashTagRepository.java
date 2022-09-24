package com.frederikzwartbol.springbootjpamanytomany.repository;

import com.frederikzwartbol.springbootjpamanytomany.models.entity.Hashtag;
import com.frederikzwartbol.springbootjpamanytomany.repository.aggregates.HashtagCountAggregate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HashTagRepository extends JpaRepository<Hashtag,Long> {
    List<Hashtag> findHashtagsByMessageContaining(String message);
    List<Hashtag> findAllByMessageIgnoreCaseIn(List<String> message);
    Optional<Hashtag> findHashtagByMessage(String message);

    @Query(value = "SELECT h.id as hashtagId, h.message as hashtagMessage," +
            "h.tweets.size as hashtagCount FROM Hashtag h ORDER BY hashtagCount DESC ")
    List<HashtagCountAggregate> getHashtagMentions();
}
