package com.example.encore_spring_pjt.jpaPractice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Entity(name = "ENCORE_BOARD_TBL")
public class BoardEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "writer")
    private String writer;
    @Column(name = "view_cnt")
    private Integer viewCnt;
    @Column(name = "notice_yn")
    private boolean noticeYn;
    @Column(name = "secret_yn")
    private boolean secretYn;
    @Column(name = "delete_yn")
    private boolean deleteYn;
    @Column(name = "insert_time")
    private LocalDateTime insertTime;
    @Column(name = "update_time")
    private LocalDateTime upDateTime;

}
