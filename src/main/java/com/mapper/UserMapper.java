package com.mapper;

import com.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    @Select("SELECT id, username, password, is_active as isActive, role, date_of_birth as dateOfBirth, e_mail as eMail, avatar, department, job_title as jobTitle FROM user WHERE username = #{username}")
    User selectUserByUsername(String username);

    @Insert("INSERT INTO user(id, username, password, is_active, role, date_of_birth, e_mail, avatar, department, job_title) VALUES (#{id}, #{username}, #{password}, #{isActive}, #{role}, #{dateOfBirth}, #{email}, #{avatar}, #{department}, #{jobTitle})")
    void insertUser(String id, String username, String password, String isActive, String role, String dateOfBirth, String email, String avatar, String department, String jobTitle);

    @Select("SELECT id, username, password, is_active as isActive, role, date_of_birth as dateOfBirth, e_mail as eMail, avatar, department, job_title as jobTitle FROM user ORDER BY role")
    List<User> selectAllUsers();
    
    @Update("UPDATE user SET password = #{password} WHERE id = #{id}")
    void updatePassword(String id, String password);

    @Select("SELECT password FROM user WHERE id = #{id}")
    String selectPasswordById(String id);

    @Update("UPDATE user SET is_active = 1 WHERE id = #{id}")
    void activeUser(String id);

    @Update("UPDATE user SET avatar = #{avatar} WHERE id = #{id}")
    void updateAvatar(String id, String avatar);

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUserById(String id);

    @Select("SELECT id, username, password, is_active as isActive, role, date_of_birth as dateOfBirth, e_mail as eMail, avatar, department, job_title as jobTitle FROM user WHERE id = #{id}")
    User selectUserById(String id);

    @Update("UPDATE user SET username = #{username}, date_of_birth = #{dateOfBirth}, e_mail = #{eMail}, department = #{department}, job_title = #{jobTitle} WHERE id = #{id}")
    void updateProfile(String id, String username, String dateOfBirth, String eMail, String department, String jobTitle);
}
