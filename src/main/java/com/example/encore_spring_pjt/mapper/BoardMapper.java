package com.example.encore_spring_pjt.mapper;

import java.util.List;
import java.util.Optional;

import com.example.encore_spring_pjt.paging.PageDTO;
import org.apache.ibatis.annotations.Mapper;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;

/*
 * encore_board_tbl과 CRUD 작업을 위한 추상메서드 선언
 * Mapper : DAO와 동일한 작업을 진행
 */
@Mapper
public interface BoardMapper {
    
    // insert
    public void save(BoardRequest params);
    // select (single finder)
    public Optional<BoardResponse> findByIdx(BoardRequest params);
    // update (title, content, writer) - idx
    public void updateByIdx(BoardRequest params);
    // 레코드의 건수를 count
    //public int count();

    // 페이징 처리를 위한 수정
    public int count(PageDTO params);



    // delete idx
    public void deleteByIdx(BoardRequest params);
    // select (multi finder)
    //  public List<BoardResponse> findAll();

    // 페이지 처리를 위한 수정
    public List<BoardResponse> findAll(PageDTO params);

    public void updateByCnt(BoardRequest params);

    
}
