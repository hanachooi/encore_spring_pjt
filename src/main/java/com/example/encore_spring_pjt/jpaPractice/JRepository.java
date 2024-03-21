package com.example.encore_spring_pjt.jpaPractice;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<엔티티 이름, 엔티티의 PK 타입>
public interface JRepository extends JpaRepository<BoardEntity, Integer> {

    // 기본 함수들은 함수 선언 없이 그냥 구현이 가능

    // 따로 호출해야 할 것들은 @Query(JPQL) 을 이용해서, 호출할 수 있음.


}
