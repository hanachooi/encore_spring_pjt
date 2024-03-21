package com.example.encore_spring_pjt.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
import com.example.encore_spring_pjt.paging.PageDTO;
import com.example.encore_spring_pjt.paging.PageResponse;
import com.example.encore_spring_pjt.paging.Pagination;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.mapper.BoardMapper;


import lombok.RequiredArgsConstructor;

// BoardService boardServiceImpl = new BoardServiceImpl();
// 객체를 생성한 후 spring container에서 관리하도록 하는 것
// MyBatis를 이용할 경우 ,,,
@Service("board")
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
 
    private final BoardMapper boardMapper;

    //@Autowired : 의존성을 주입받는 DI 방식
    // @Autowired
    // private BoardMapper boardMapper;

    // 오류가 발생하면 rollback, 성공적으로 수행되면 auto commit
    // DML에만 사용한다.
    @Transactional
    @Override
    public Integer saveBoard(BoardRequest params) {
        System.out.println("debug >>>> board service saveBoard : " + boardMapper);
        boardMapper.save(params);

        return params.getIdx();
        // null이 아니라 
    }

    @Override
    public Optional<BoardResponse> findBoard(BoardRequest params) {
        boardMapper.updateByCnt(params);

        Optional<BoardResponse> response = boardMapper.findByIdx(params);

        return response;
    }

    @Transactional
    @Override
    public Integer updateBoard(BoardRequest params) {

        boardMapper.updateByIdx(params);

        return params.getIdx();
    }

    @Override
    public Integer deleteBoard(BoardRequest params) {

        boardMapper.deleteByIdx(params);

        return params.getIdx();

    }

    @Override
    public PageResponse<BoardResponse> listBoard(PageDTO param) {

        // 페이징 + 페이지네이션 처리 : 전체 레코드 수가 필요하다.
        int recordCnt = boardMapper.count(param);

        // 게시글이 없을 때
        if(recordCnt <= 0){
            return new PageResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 이용해 계산을 하기 위해서 param 객체를 넘겨주어야 한다.
        Pagination pagination = new Pagination( recordCnt , param);

        param.setPagination(pagination);    // BoardMapper.xml에서 limitStart를 사용해야 하니까
        List<BoardResponse> list = boardMapper.findAll(param);

        return new PageResponse<BoardResponse>(list, pagination);
    }

    @Override
    public Integer cntBoard(PageDTO param) {
        return boardMapper.count(param);
    }

    public void findBoardNotCnt(BoardRequest params) {

        boardMapper.updateByCnt(params);

        Optional<BoardResponse> response = boardMapper.findByIdx(params);

    }
}
