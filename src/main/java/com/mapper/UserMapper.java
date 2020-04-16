package com.mapper;

import com.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    @Select("SELECT id, username, password, is_active as isActive, role, date_of_birth as dateOfBirth, e_mail as eMail FROM user WHERE username = #{username}")
    User selectUserByUsername(String username);

    @Insert("INSERT INTO user(id, username, password, is_active, role, date_of_birth, e_mail) VALUES (#{id}, #{username}, #{password}, #{isActive}, #{role}, #{dateOfBirth}, #{email})")
    void insertUser(String id, String username, String password, String isActive, String role, String dateOfBirth, String email);

    @Select("SELECT id, username, password, is_active as isActive, role, date_of_birth as dateOfBirth, e_mail as eMail FROM user")
    List<User> selectAllUsers();
    
    @Update("UPDATE user SET password = #{password} WHERE id = #{id}")
    void updatePassword(String id, String password);

    @Select("SELECT password FROM user WHERE id = #{id}")
    String selectPasswordById(String id);

    @Update("UPDATE user SET is_active = 1 WHERE id = #{id}")
    void activeUser(String id);
}
