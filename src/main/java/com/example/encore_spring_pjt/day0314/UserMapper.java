package com.example.encore_spring_pjt.day0314;

import com.example.encore_spring_pjt.ctrl.user.domain.UserRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public UserResponse loginRow(UserRequest params);

    public void registerRow(UserRequest params);

}
