package com.frederikzwartbol.springbootjpamanytomany;

import com.frederikzwartbol.springbootjpamanytomany.authentication.entity.Credentials;
import com.frederikzwartbol.springbootjpamanytomany.authentication.entity.Role;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.Media;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.MediaTypeFormat;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.user.ProfileInformation;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.Tweet;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.user.User;
import com.frederikzwartbol.springbootjpamanytomany.repository.HashTagRepository;
import com.frederikzwartbol.springbootjpamanytomany.authentication.repository.RoleRepository;
import com.frederikzwartbol.springbootjpamanytomany.repository.TweetRepository;
import com.frederikzwartbol.springbootjpamanytomany.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringbootJpaManytomanyApplication implements CommandLineRunner {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;
    private final HashTagRepository hashTagRepository;
    private final RoleRepository roleRepository;

    public SpringbootJpaManytomanyApplication(TweetRepository tweetRepository, UserRepository userRepository, HashTagRepository hashTagRepository, RoleRepository roleRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
        this.hashTagRepository = hashTagRepository;
        this.roleRepository = roleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaManytomanyApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Set<Role> roles = new HashSet<>();

        User frederik = User.builder()
                .firstName("Frederik")
                .lastName("Zwartbol")
                .credentials(new Credentials(null,"frederikzwartbol@gmail.com",passwordEncoder().encode("geheim12"),roles))
                .profileImage("/profileImage/Frederik.jpg")
                .profileInformation(new ProfileInformation( "Java Developer","Loves Programming","/profileBackground/incentro-background.png","Amsterdam","https://www.frederikzwartbol.com/"))
                .twitterHandle("Freddie")
                .following(new HashSet<>())
                .followers(new HashSet<>())
                .tweets(new HashSet<>())
                .build();

        frederik.getProfileInformation().setUser(frederik);
        Tweet tweet = new Tweet("Dit is mijn eerst tweet! #firstTweet", LocalDateTime.now());
        tweet.setMedia(new Media(null,"link", MediaTypeFormat.VIDEO,tweet));
        tweet.setUser(frederik);
        frederik.getTweets().add(tweet);
        this.userRepository.save(frederik);

        User dennis = User.builder()
                .firstName("Dennis")
                .lastName("Vonk")
                .credentials(new Credentials(null,"admin1@admin.com",passwordEncoder().encode("admin"),roles))
                .profileImage("/profileImage/Dennis.jpg")
                .profileInformation(new ProfileInformation("","","/profileBackground/incentro-background.png",null,null))
                .twitterHandle("DennisVonk")
                .following(new HashSet<>())
                .followers(new HashSet<>())
                .tweets(new HashSet<>())
                .build();

        dennis.getProfileInformation().setUser(dennis);
        Tweet tweet2 = new Tweet("Hey, wat leuk. Doet alles het al?", LocalDateTime.now());
        tweet2.setUser(dennis);
        dennis.getTweets().add(tweet2);
        dennis.setFollowers(Set.of(frederik));
        this.userRepository.save(dennis);


        User cumberbatch = User.builder()
                .firstName("David")
                .lastName("Cumberbatch")
                .credentials(new Credentials(null,"admin2@admin.com",passwordEncoder().encode("admin"),roles))
                .profileImage("/profileImage/cumberbatch.jpg")
                .profileInformation(new ProfileInformation( "","",null,null,null))
                .twitterHandle("TheCumberbatch")
                .following(new HashSet<>())
                .followers(new HashSet<>())
                .tweets(new HashSet<>())
                .build();
        cumberbatch.getProfileInformation().setUser(cumberbatch);
        this.userRepository.save(cumberbatch);

        frederik.setFollowers(Set.of(cumberbatch));

        User theRock = User.builder()
                .firstName("Dwayne")
                .lastName("Johnson")
                .credentials(new Credentials(null,"admin3@admin.com",passwordEncoder().encode("admin"),roles))
                .profileImage("/profileImage/therock.jpeg")
                .profileInformation(new ProfileInformation("","","/profileBackground/incentro-background.png",null,null))
                .twitterHandle("TheRock")
                .following(new HashSet<>())
                .followers(new HashSet<>())
                .tweets(new HashSet<>())
                .build();

        theRock.getProfileInformation().setUser(theRock);

        Tweet tweet4 = new Tweet("Have you seen my new movie?", LocalDateTime.now());
        tweet4.setUser(theRock);
        theRock.getTweets().add(tweet4);

        this.userRepository.save(theRock);

        User elon = User.builder()
                .firstName("Elon")
                .lastName("Musk")
                .credentials(new Credentials(null,"admin4@admin.com",passwordEncoder().encode("admin"),roles))
                .profileImage("/profileImage/elon.jpg")
                .profileInformation(new ProfileInformation("","","/profileBackground/musk-spacex.jpeg",null,null))
                .twitterHandle("elonmusk")
                .following(new HashSet<>())
                .followers(new HashSet<>())
                .tweets(new HashSet<>())
                .build();

        elon.getProfileInformation().setUser(elon);
        Tweet tweet5 = new Tweet("The new SpaceX falcon flight will go to Mars!", LocalDateTime.now());
        tweet5.setUser(elon);
        elon.getTweets().add(tweet5);
        elon.setFollowers(Set.of(frederik));
        this.userRepository.save(elon);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
