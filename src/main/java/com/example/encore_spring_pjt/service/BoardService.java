package com.example.encore_spring_pjt.service;

import java.util.List;
import java.util.Optional;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.paging.PageDTO;
import com.example.encore_spring_pjt.paging.PageResponse;

public interface BoardService {
    
    public Integer saveBoard(BoardRequest params);
    public Optional<BoardResponse> findBoard(BoardRequest params);
    public Integer updateBoard(BoardRequest params);
    public Integer deleteBoard(BoardRequest params);

    // 페이지 처리로 인자가 변경
    // public List<BoardResponse> listBoard();
    //public Integer cntBoard();
// 페이징 처리
    // public List<BoardResponse> listBoard();
    public PageResponse<BoardResponse> listBoard(PageDTO param);
    // public Integer cntBoard();
    public Integer cntBoard(PageDTO param);

}
