package com.frederikzwartbol.springbootjpamanytomany.repository;

import com.frederikzwartbol.springbootjpamanytomany.models.entity.Category;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.Hashtag;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.MediaTypeFormat;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.Tweet;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.user.User;
import com.frederikzwartbol.springbootjpamanytomany.repository.aggregates.TweetCountAggregate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TweetRepository extends JpaRepository<Tweet,Long> {

    /** Find all tweets */
    Page<Tweet> findAllByParentTweetIsNull(Pageable page);
    Page<Tweet> findAllByHashtagsIn(Pageable page, List<Hashtag> hashtags);
    Page<Tweet> findAllByHashtagsInAndMedia_Type(List<Hashtag> hashtags, MediaTypeFormat media, Pageable page);
    Page<Tweet> findAllByCategoryIn(Pageable page, Set<Category> categories);
    Page<Tweet> findAllByCategory_CategoryName(Pageable pageable, String categoryName);

    /** Find all by user */
    Page<Tweet> findAllByUser_Id(Long id, Pageable page);
    Page<Tweet> findAllByParentTweetIsNullAndUser_Id(Long id, Pageable page);
    Page<Tweet> findAllByLikes_userId(Long userId, Pageable page);
    Page<Tweet> findAllByUser_IdAndMedia_idIsNotNull(Long userId, Pageable page);

    @Query(value = "SELECT tweet_id as tweetId, " +
            "count(tweet_id) as likeCount from likes " +
            "group by tweet_id ORDER BY likeCount DESC fetch first 10 rows only;", nativeQuery = true)
    List<TweetCountAggregate> getTopLikedTweets();


    @Query(value = "SELECT tweet_id as tweetId, " +
            "count(tweet_id) as replyCount from tweets_replies " +
            "group by tweet_id ORDER BY replyCount DESC fetch first 10 rows only;", nativeQuery = true)
    List<TweetCountAggregate> getTopRepliedTweets();

    /** Tweet liked by user */
    @Query(value = "SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END " +
            "FROM tweets t WHERE t.id = :tweetId AND t.user_id = :userId", nativeQuery = true)
    Boolean tweetLikedByUser(@Param("tweetId") Long tweetId, @Param("userId") Long userId);

    /** Native SQL query:
     * Tweet isReplied by User */
    @Query(value = "SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END" +
            " FROM tweets t RIGHT JOIN " +
            "(SELECT r.tweet_id, r.replies_id from tweets_replies r WHERE r.tweet_id = :tweetId) as rt " +
            "ON t.Id = rt.replies_id where t.user_id = :userId" +
            "", nativeQuery = true)
    Boolean tweetRepliedByUser(@Param("tweetId") Long tweetId, @Param("userId") Long userId);

    //find tweets by followed user
    @Query(value = "SELECT * from tweets t JOIN followers f on t.user_id = f.follower_id WHERE f.follower_id = :userId  ", nativeQuery = true)
    Page<Tweet> findFollowUser(@Param("userId") Long userId, Pageable pageable);


    @Query(value = "Select t FROM Tweet t WHERE t.user IN (SELECT u1 FROM User u1 WHERE :user MEMBER OF u1.followers) order by t.publicationDate")
    Page<Tweet> findTweetsForFollowing(User  user, Pageable pageable);


}
