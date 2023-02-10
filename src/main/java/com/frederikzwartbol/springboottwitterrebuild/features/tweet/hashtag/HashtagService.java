package com.frederikzwartbol.springboottwitterrebuild.features.tweet.hashtag;

import com.frederikzwartbol.springboottwitterrebuild.features.tweet.models.dto.TweetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class HashtagService {
    private final HashTagRepository hashTagRepository;
    public Hashtag saveHashtagByName(String hashtagName) {
        Hashtag hashTag = new Hashtag(hashtagName);
        return hashTagRepository.save(hashTag);
    }

    public Hashtag saveHashtag(Hashtag hashTag) {
        return hashTagRepository.save(hashTag);
    }

    public List<Hashtag> findHashtagsByMessage(String hashtagName) {
        return hashTagRepository.findHashtagsByMessageContaining(hashtagName);
    }

    public List<Hashtag> findHashtagsByMessage(List<String> messages) {
        return hashTagRepository.findAllByMessageIgnoreCaseIn(messages.stream()
                .map(message -> parseSearchQuery(message)).collect(Collectors.toList()));
    }

    /**
     * Parses hashtags from within the message of a tweetRequest
     *
     * @param request
     * @return List<Hashtag>
     */
    public Set<Hashtag> parseHashTagsFromRequest(TweetRequest request) {
        Pattern getHashtagPattern = Pattern.compile("#(\\S+)");
        var mat = getHashtagPattern.matcher(request.message());
        List<String> hashtagsInTweet = new ArrayList<>();
        while (mat.find()) {
            hashtagsInTweet.add(mat.group(1));
        }
        return hashtagsInTweet.stream()
                .map(hashtagName -> findHashtagByName(hashtagName)
                        .orElse(new Hashtag(hashtagName, LocalDateTime.now()))).collect(Collectors.toSet());
    }

    public Optional<Hashtag> findHashtagByName(String hashtagName) {
        return hashTagRepository.findHashtagByMessage(hashtagName);
    }

    public String parseSearchQuery(String message) {
        return message.replaceAll("#", "");
    }
}
