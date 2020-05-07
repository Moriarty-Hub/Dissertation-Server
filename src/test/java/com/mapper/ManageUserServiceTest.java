package com.mapper;

import com.entity.Role;
import com.service.MailService;
import com.service.ManageUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ManageUserServiceTest {

    @Mock
    private MailService mailService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private ManageUserService manageUserService;

    @Test
    public void testInsertNewUserAndSendAccountInfo() {
        String username = "temp";
        String email = "test@test.com";
        String dateOfBirth = "2020-01-01";
        String department = "Development";
        String jobTitle = "Developer";
        manageUserService.insertNewUserAndSendAccountInfo(username, email, dateOfBirth, department, jobTitle);
        verify(userMapper, times(1)).insertUser(anyString(), anyString(), anyString(), anyString(),
                anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
        verify(mailService, times(1)).sendMail(anyString(), anyString(), anyString());
    }
}
