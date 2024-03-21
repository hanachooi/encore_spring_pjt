package com.example.encore_spring_pjt.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class BoardResponse {

    private Integer idx;
    private String title;
    private String content;
    private String writer;
    private Integer viewCnt;
    private boolean noticeYn;
    private boolean secretYn;
    private boolean deleteYn;
    private LocalDateTime insertTime;
    private LocalDateTime upDateTime;

}
