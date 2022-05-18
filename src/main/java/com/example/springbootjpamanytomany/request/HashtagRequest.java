package com.example.springbootjpamanytomany.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HashtagRequest {
    private final String name;
}
