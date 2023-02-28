package com.frederikzwartbol.springboottwitterrebuild.features.trending;

import com.frederikzwartbol.springboottwitterrebuild.features.trending.aggregates.HashtagCountAggregate;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.Tweet;
import com.frederikzwartbol.springboottwitterrebuild.features.category.CategoryRepository;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.hashtag.HashTagRepository;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.TweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrendingGlobalService {

    private final HashTagRepository hashTagRepository;
    private final TweetRepository tweetRepository;
    private final CategoryRepository categoryRepository;

    public List<Tweet> getTopRepliedTweets() {
        return tweetRepository.getTopRepliedTweets()
                .stream()
                .map(e ->
                        tweetRepository.findById(e.getTweetId()).get())
                        .collect(Collectors.toList());
    }

    public List<Tweet> getTopLikedTweets() {
        return tweetRepository.getTopLikedTweets()
                .stream()
                .map(e ->
                        tweetRepository.findById(e.getTweetId()).get())
                        .collect(Collectors.toList());
    }

    public List<HashtagCountAggregate> getTopHashtags() {
        return hashTagRepository.getHashtagMentions()
                .stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Page<Tweet>> getTopByCategory(Optional<Integer> page, Optional<String> sortBy) {
        return categoryRepository.findAll().stream().map(
                category ->
                        tweetRepository.findAllByCategory_CategoryName(
                                category.getCategoryName(),
                                PageRequest.of(
                                        page.orElse(0), 5,
                                        Sort.by(Sort.Direction.DESC, sortBy.orElse("id"))
                                ))).collect(Collectors.toList());
    }

}
