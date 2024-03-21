package com.example.encore_spring_pjt.day0314;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
    private String id;
    private String pwd;
    private String name;
    private Integer point;
}
