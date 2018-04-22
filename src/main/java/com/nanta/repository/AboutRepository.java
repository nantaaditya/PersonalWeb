package com.nanta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nanta.entity.About;

@Repository
public interface AboutRepository extends JpaRepository<About, String> {
}
