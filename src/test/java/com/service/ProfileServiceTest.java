package com.service;

import com.entity.User;
import com.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private MultipartFile avatar;

    @Mock
    private SecurityContextHolder securityContextHolder;

    @InjectMocks
    private ProfileService profileService;

    @Test(expected = NullPointerException.class)
    public void should_throw_NullPointerException_when_filename_is_null() throws IOException {
        when(avatar.getOriginalFilename()).thenReturn(null);
        profileService.updateAvatar("", avatar);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_IllegalArgumentException_when_suffix_name_is_invalid() throws IOException {
        when(avatar.getOriginalFilename()).thenReturn("default.gif");
        profileService.updateAvatar("", avatar);
    }

    @Test
    public void should_update_avatar_when_filename_exist_and_suffix_name_is_valid() throws IOException {
        when(avatar.getOriginalFilename()).thenReturn("default.jpeg");
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(new User());
        profileService.updateAvatar("", avatar);
        verify(avatar, times(1)).transferTo(any(File.class));
    }
}
