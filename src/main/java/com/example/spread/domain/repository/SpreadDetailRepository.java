package com.example.spread.domain.repository;

import com.example.spread.domain.receive.entity.SpreadDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpreadDetailRepository extends JpaRepository<SpreadDetail, Long> {
    SpreadDetail findByUserId(Long userId);

    List<SpreadDetail> findUnallocatedDetailBySpreadId(Long spreadId);

    List<SpreadDetail> findBySpreadIdAndUserIdIsNotNull(Long id);

}
