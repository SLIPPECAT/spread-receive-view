package com.example.spread.spread.repository;

import com.example.spread.receive.entity.Spread;
import com.example.spread.receive.entity.SpreadDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpreadDetailRepository extends JpaRepository<SpreadDetail, Long> {
    List<SpreadDetail> findUnallocatedDetailBySpreadId(Long spreadId);

    SpreadDetail findByUserId(Long userId);

    List<SpreadDetail> findBySpreadIdAndUserIdIsNotNull(Long id);

//
//    SpreadDetail findByToken(String token);

//    SpreadDetail findByUserIdAndToken(Long userId, String token);

//    @Query("SELECT d FROM SpreadDetail d WHERE d.spread.id = :spreadId AND d.allocated = false")
//    SpreadDetail findUnallocatedDetailBySpreadId(@Param("spreadId") Long spreadId);

}
