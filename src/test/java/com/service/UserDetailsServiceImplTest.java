package com.service;

import com.entity.User;
import com.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test(expected = UsernameNotFoundException.class)
    public void should_throw_UsernameNotFoundException_when_username_not_exist() {
        when(userMapper.selectUserByUsername("unknownUser")).thenReturn(null);
        UserDetails userDetails = userDetailsService.loadUserByUsername("unknownUser");
        assertNull(userDetails);
    }

    @Test
    public void should_return_UserDetails_when_username_exist() {
        when(userMapper.selectUserByUsername("root")).thenReturn(new User());
        UserDetails userDetails = userDetailsService.loadUserByUsername("root");
        assertNotNull(userDetails);
    }
}
