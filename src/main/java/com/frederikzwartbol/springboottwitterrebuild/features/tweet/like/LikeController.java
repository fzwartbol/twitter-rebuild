package com.frederikzwartbol.springboottwitterrebuild.features.tweet.like;

import com.frederikzwartbol.springboottwitterrebuild.features.tweet.like.model.LikeRequest;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.like.model.LikeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController implements LikeOperations {
    private final LikeService likeService;

    @Override
    public ResponseEntity<?> checkLike(@PathVariable("tweetId") int tweetId, @RequestBody LikeRequest request) {
        LikeResponseDTO likeResponseDTO = likeService.checkLike(request);
        return ResponseEntity.ok(likeResponseDTO);
    }

    @Override
    public ResponseEntity<?> deleteLike(LikeRequest request) {
        return ResponseEntity.ok("ok");
    }

}
