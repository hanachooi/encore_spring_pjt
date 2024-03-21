package com.example.encore_spring_pjt.ctrl.user.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class UserRequest {

    // 유효성 체크를 위함.
    @NotBlank(message = "필수")
    private String id;
    @NotBlank(message = "입력")
    private String pwd;
    @NotBlank(message = "사항입니다.")
    private String name;

}
