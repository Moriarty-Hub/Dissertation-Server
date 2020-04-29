package com.service;

import com.entity.User;
import com.mapper.UserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Service
public class ProfileService {

    private static String AVATAR_FOLDER_PATH = "";
    private final UserMapper userMapper;

    public ProfileService(UserMapper userMapper) {
        try {
            AVATAR_FOLDER_PATH = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.userMapper = userMapper;
    }

    public void updateAvatar(String id, MultipartFile avatar) throws IOException {
        String filename = avatar.getOriginalFilename();
        if (filename == null) {
            throw new NullPointerException("The file cannot be null");
        }
        String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
        if ((!suffixName.equals("jpe")) && (!suffixName.equals("jpg")) && (!suffixName.equals("jpeg"))) {
            throw new IllegalArgumentException("The file format is invalid");
        }
        filename = UUID.randomUUID().toString() + "." + suffixName;
        File destination = new File(AVATAR_FOLDER_PATH + "static/avatar/" + filename);
        avatar.transferTo(destination);
        userMapper.updateAvatar(id, filename);
        ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).setAvatar(filename);
    }

}
