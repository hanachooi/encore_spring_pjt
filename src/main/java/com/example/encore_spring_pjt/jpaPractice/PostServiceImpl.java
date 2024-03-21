package com.example.encore_spring_pjt.jpaPractice;

import java.util.List;
import java.util.Optional;

import com.example.encore_spring_pjt.ctrl.rest.dao.postRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.mapper.BoardMapper;
// JPA 이용
@Service
@RequiredArgsConstructor
public class PostServiceImpl{

    // 서비스에서는 repository를 불러옴. ,, dto는 BoardEntity
    private final postRepository repository;

    public Integer saveBoard(BoardRequest params) {

        return null;
    }


   public List<BoardEntity> findByName(){
        return null;
   }


    public Integer updateBoard(BoardRequest params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBoard'");
    }


    public Integer deleteBoard(BoardRequest paramse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteBoard'");
    }


    // 전체 조회 업무
    // 여기에서는 그냥 LIST 형태로 RETURN 하면 됌.
    public List<BoardEntity> findAllBoard() {
        System.out.println("PostServiceImpl.listBoard" + repository);
        return repository.findAll();
    }


    public Integer cntBoard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cntBoard'");
    }
    
}
