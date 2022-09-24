package com.frederikzwartbol.springbootjpamanytomany.controller.tweet;

import com.frederikzwartbol.springbootjpamanytomany.controller.tweet.operations.LikeOperations;
import com.frederikzwartbol.springbootjpamanytomany.models.DTO.LikeResponseDTO;
import com.frederikzwartbol.springbootjpamanytomany.models.request.LikeRequest;
import com.frederikzwartbol.springbootjpamanytomany.service.LikeService;
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
