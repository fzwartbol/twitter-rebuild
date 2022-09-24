package com.frederikzwartbol.springbootjpamanytomany.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class LikeResponseDTO implements Serializable {
    private Long userId;
    private Integer likeCount;
    private Boolean postLiked;
}
