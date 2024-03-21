package com.example.encore_spring_pjt.ctrl.rest.dao;

import com.example.encore_spring_pjt.jpaPractice.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface postRepository extends JpaRepository<BoardEntity, Integer> {

    // 우리가 사용하는 dto 는 BoardEntity

    // JPA, JPQL
    // findByXXXX



}
