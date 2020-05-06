package com.mapper;

import com.entity.Role;
import com.entity.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Before
    public void insertTempUser() {
        String id = "21454c62-7747-4388-91fe-db994ae83c0c";
        String username = "temp";
        String password = "$2a$10$NXiJXGjbYxgr5qNs/ZmTUeUsRmvLa0.vUzqickRqosxu0/duMyRBK";
        String isActive = "0";
        String role = Role.NORMAL_USER.toString();
        String dateOfBirth = "1998-12-31";
        String email = "temp@gmail.com";
        String avatar = "default.jpg";
        String department = "Testing";
        String jobTitle = "Unassigned";
        userMapper.insertUser(id, username, password, isActive, role, dateOfBirth, email, avatar, department, jobTitle);
    }

    @After
    public void deleteTempUser() {
        userMapper.deleteUserById("21454c62-7747-4388-91fe-db994ae83c0c");
    }

    @Test
    public void testSelectUserByUsername() {
        User user = userMapper.selectUserByUsername("root");
        assertEquals("4a4b1a8d-09db-49dc-898e-f8d20e671892", user.getId());
        assertEquals("root", user.getUsername());
        assertEquals("$2a$10$YhNqn5H5uvaqfLYgvdL0QuhESpIX1UF.b3zgcquibDudiRTYz9j0u", user.getPassword());
        assertTrue(user.isActive());
        assertEquals(Role.ADMINISTRATOR, user.getRole());
        assertEquals("1998-02-21", user.getDateOfBirth());
        assertEquals("this_is_dxr@qq.com", user.getEMail());
        assertEquals("313c6a18-a00a-4e76-a431-3dfb7be33c88.jpg", user.getAvatar());
        assertEquals("Research&Development", user.getDepartment());
        assertEquals("Senior Software Engineer", user.getJobTitle());
    }

    @Test
    public void testInsertUser() {
        User user = userMapper.selectUserByUsername("xdong1");
        assertNull(user);
        String id = "554cb6ac-db45-4ecb-883e-e3a906509aba";
        String username = "xdong1";
        String password = "$2a$10$QO75812uYiZB7wrnDKwx7.UT45OC58GIJ.FGvRSyQUZYQjl3P8GdS";
        String isActive = "0";
        String role = Role.NORMAL_USER.toString();
        String dateOfBirth = "1998-01-01";
        String email = "xdong1@gmail.com";
        String avatar = "default.jpg";
        String department = "Research & Development";
        String jobTitle = "Intern";
        userMapper.insertUser(id, username, password, isActive, role, dateOfBirth, email, avatar, department, jobTitle);
        User insertedUser = userMapper.selectUserByUsername("xdong1");
        assertNotNull(insertedUser);
        assertEquals(id, insertedUser.getId());
        assertEquals(username, insertedUser.getUsername());
        assertEquals(password, insertedUser.getPassword());
        assertEquals(Boolean.valueOf(isActive), insertedUser.isActive());
        assertEquals(role, insertedUser.getRole().toString());
        assertEquals(dateOfBirth, insertedUser.getDateOfBirth());
        assertEquals(email, insertedUser.getEMail());
        assertEquals(avatar, insertedUser.getAvatar());
        assertEquals(department, insertedUser.getDepartment());
        assertEquals(jobTitle, insertedUser.getJobTitle());
        userMapper.deleteUserById("554cb6ac-db45-4ecb-883e-e3a906509aba");
    }

    @Test
    public void testSelectAllUsers() {
        List<User> userList = userMapper.selectAllUsers();
        assertEquals(3, userList.size());
    }

    @Test
    public void testSelectPasswordById() {
        String password = userMapper.selectPasswordById("21454c62-7747-4388-91fe-db994ae83c0c");
        String expectedPassword = "$2a$10$NXiJXGjbYxgr5qNs/ZmTUeUsRmvLa0.vUzqickRqosxu0/duMyRBK";
        assertEquals(expectedPassword, password);
    }

    @Test
    public void testUpdatePassword() {
        String oldPassword = "$2a$10$NXiJXGjbYxgr5qNs/ZmTUeUsRmvLa0.vUzqickRqosxu0/duMyRBK";
        String newPassword = "$2a$10$dadwCl/7/hQIhcDvSuC8O.BmTTxsRrWbOuU5BLoIT7jZBUZee3Mgu";
        assertEquals(oldPassword, userMapper.selectPasswordById("21454c62-7747-4388-91fe-db994ae83c0c"));
        userMapper.updatePassword("21454c62-7747-4388-91fe-db994ae83c0c", "$2a$10$dadwCl/7/hQIhcDvSuC8O.BmTTxsRrWbOuU5BLoIT7jZBUZee3Mgu");
        assertEquals(newPassword, userMapper.selectPasswordById("21454c62-7747-4388-91fe-db994ae83c0c"));
    }

    @Test
    public void testActiveUser() {
        User user = userMapper.selectUserByUsername("temp");
        assertFalse(user.isActive());
        userMapper.activeUser("21454c62-7747-4388-91fe-db994ae83c0c");
        user = userMapper.selectUserByUsername("temp");
        assertTrue(user.isActive());
    }

    @Test
    public void testUpdateAvatar() {
        User user = userMapper.selectUserByUsername("temp");
        assertEquals("default.jpg", user.getAvatar());
        userMapper.updateAvatar("21454c62-7747-4388-91fe-db994ae83c0c", "customProfile.jpg");
        user = userMapper.selectUserByUsername("temp");
        assertEquals("customProfile.jpg", user.getAvatar());
    }

}
