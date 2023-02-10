package com.frederikzwartbol.springboottwitterrebuild.features.tweet.like.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeResponseDTO {
    private Long userId;
    private Integer likeCount;
    private Boolean postLiked;
}
