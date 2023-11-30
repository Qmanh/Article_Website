package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.dto.NewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.NewEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NewRepository extends JpaRepository<NewEntity, Long> {
    List<NewEntity> findAllByCategoryCode(String categoryCode);

    @Query("SELECT n FROM NewEntity n WHERE n.title LIKE %?1%")
    List<NewEntity> search(String keyword);

    List<NewEntity>findByTitleLike(String keyword,Pageable pageable);
    List<NewEntity>findAllByCategoryCode(String categoryCode,Pageable pageable);
}
