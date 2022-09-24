//package com.frederikzwartbol.springbootjpamanytomany.controller.category;
//
//import com.frederikzwartbol.springbootjpamanytomany.repository.TweetRepository;
//import com.frederikzwartbol.springbootjpamanytomany.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/test")
//public class TestController {
//
//    private final UserRepository userRepository;
//    private final TweetRepository repository;
//
//    @GetMapping("/testing")
//    public ResponseEntity<?> updateTweet() {
//        return ResponseEntity.ok(userRepository.findFollowersOfUser( userRepository.getById(1L),
//                PageRequest.of(
//                        0, 5)));
//    }
//
//    @GetMapping("/testing2")
//    public ResponseEntity<?> findTweetsFollowing() {
//        return ResponseEntity.ok(repository.findTweetsForFollowing( userRepository.getById(1L)
//               ));
//    }
//}
