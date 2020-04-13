package com.mapper;

import com.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Select("SELECT id, username, password, is_original_password, role, date_of_birth, e_mail FROM user WHERE username = #{username}")
    User selectUserByUsername(String username);

}
