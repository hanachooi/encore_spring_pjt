package com.example.encore_spring_pjt.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString

public class Pagination {

    // 전체 데이터 수
    private int totalRecordCnt;
    // 전체 페이지 수
    private int totalPageCnt;
    // 첫, 마지막 페이지 번호(페이지 하단의 페이지바에서의 번호)
    private int startPage;
    private int endPage;

    // limit offset 번호
    private int limitStart;

    // 이전페이지, 다음 페이지 존재 여부
    private boolean existPreviousPage;
    private boolean existNextPage;

    public Pagination(){
    }

    public Pagination(int totalRecordCnt, PageDTO param){

        if(totalRecordCnt > 0){
            this.totalRecordCnt = totalRecordCnt;
            calc(param);
        }

    }

    public void calc(PageDTO param){

        // 전체 페이지 수
        this.totalPageCnt = (int) Math.ceil(Double.valueOf(this.totalRecordCnt / param.getRecordSize()));

        // 현재 페이지 번호가 전체 페이지 수보다 큰 경우
        // 현재 페이지에 전체 페이지 수를 저장
        if(param.getPage() > this.totalPageCnt){
            param.setPage(this.totalPageCnt);
        }

        // 첫 페이지 번호 계산 : 첫번째 페이지 번호
        startPage = ((param.getPage()-1) / param.getPageSize()) * param.getPageSize();

        // 마지막 페이지 번호 계산
        this.endPage = this.startPage + param.getPageSize() -1 ;

        // 마지막 페이지번호가 전체 페이지 수보다 큰 경우
        // 마지막 페이지에 전체 페이지 수를 저장
        if( this.endPage > this.totalPageCnt){
            endPage = this.totalPageCnt;
        }

        // limit 시작 위치(페이지마다 맨 처음에 올 게시글 번호)
        limitStart = (param.getPage() -1) * param.getRecordSize();

        // 이전 페이지 존재 여부
        existPreviousPage = (startPage != 1);

        // 다음 페이지 존재 여부
        existNextPage = (endPage * param.getRecordSize()) < totalRecordCnt;
    }
}
