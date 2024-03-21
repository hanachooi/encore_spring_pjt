package com.example.encore_spring_pjt.paging;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// List<BoardResponse> 정보 + Pagination 정보를 담는 객체로 역할
// boardService에서, 페이지 정보를 넘겨야함.
@Getter
@Setter
// List<BoardResponse> + Pagination 정보를 담는 객체
public class PageResponse<T> {

    private List<T> list = new ArrayList<>();
    private Pagination pagination;

    public PageResponse(List<T> list, Pagination pagination){
        this.list.addAll(list);
        this.pagination = pagination;
    }

}
