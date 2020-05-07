package com.service;

import com.mapper.UserMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ModifyPasswordServiceTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private ModifyPasswordService modifyPasswordService;

    @Test
    public void should_return_false_when_two_passwords_not_equal() {
        String id = "1";
        String password = "password";
        String confirmPassword = "confirmPassword";
        Map<Boolean, Optional<String>> returnValue = modifyPasswordService.setPassword(id, password, confirmPassword);
        assertTrue(returnValue.containsKey(false));
        assertTrue(returnValue.get(false).isPresent());
        assertEquals("The two password are not equals", returnValue.get(false).get());
        verify(userMapper, times(0)).updatePassword(anyString(), anyString());
        verify(userMapper, times(0)).activeUser(anyString());
    }

    @Test
    public void should_update_password_when_two_passwords_equal() {
        String id = "1";
        String password = "password";
        String confirmPassword = "password";
        Map<Boolean, Optional<String>> returnValue = modifyPasswordService.setPassword(id, password, confirmPassword);
        assertTrue(returnValue.containsKey(true));
        assertFalse(returnValue.get(true).isPresent());
        verify(userMapper, times(1)).updatePassword(anyString(), anyString());
        verify(userMapper, times(1)).activeUser(anyString());
    }
}
