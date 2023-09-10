package com.example.spread.spread.repository;

import com.example.spread.receive.entity.Spread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SpreadRepository extends JpaRepository<Spread, Long> {

//    @Query(value = "select * from spread where token = :token", nativeQuery = true)
    Optional<Spread> findByToken(@Param("token") String token);
}
