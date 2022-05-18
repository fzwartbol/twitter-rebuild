package com.example.springbootjpamanytomany.repository;

import com.example.springbootjpamanytomany.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashTagRepository extends JpaRepository<Hashtag,Long> {
}
