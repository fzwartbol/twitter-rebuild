package com.example.springbootjpamanytomany.repository;

import com.example.springbootjpamanytomany.entity.Like;
import com.example.springbootjpamanytomany.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
}
