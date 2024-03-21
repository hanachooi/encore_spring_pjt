package com.example.encore_spring_pjt.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 페이징 처리를 위해서는 몇가지의 파라미터가 필요하다.
@Getter @Setter
@ToString
public class PageDTO {

    // 현재 페이지 번호를 의미(페이지 정보 계산에 사용)
    private int page;

    // 페이지당 출력할 데이터의 개수를 의미(페이지 정보 계산에 사용)
    private int recordSize;

    // 하단의 화면이 몇개 가능한지, [1,2,3,4,5] 이거임. 화면 하단에 출력할 페이지의 크기
    private int pageSize;

    // 검색 키워드(Mybatis Dynamix Query)
    private String keyword;

    // 검색 유형을 의미 ( 제목, 작성자, 내용, etc ... )
    private String searchType;

    // offset 계산된 결과를 담기 위해서
    private Pagination pagination;

    public PageDTO(){
        this.page = 1;
        this.recordSize =2;
        this.pageSize = 10;
    }

    public int getOffset(){
        return (this.page - 1) * recordSize;
    }

}
