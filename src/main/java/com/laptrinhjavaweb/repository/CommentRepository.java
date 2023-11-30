package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.CommentEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

    Page<CommentEntity> findCommentEntitiesByNewIdOrderByCreatedDateDesc(NewEntity newEntity, Pageable pageable);
    int countCommentEntitiesByNewId(NewEntity newEntity);
    Page<CommentEntity> findByOrderByNewIdAsc(Pageable pageable);

}
