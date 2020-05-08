package com.service;

import com.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ModifyPasswordService {

    private final UserMapper userMapper;

    public ModifyPasswordService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Map<Boolean, Optional<String>> setPassword(String id, String password, String confirmPassword) {
        Map<Boolean, Optional<String>> returnValue = new HashMap<>();
        if (!password.equals(confirmPassword)) {
            returnValue.put(false, Optional.of("The two password are not equals"));
        } else {
            userMapper.updatePassword(id, new BCryptPasswordEncoder().encode(password));
            userMapper.activeUser(id);
            returnValue.put(true, Optional.empty());
        }
        return returnValue;
    }

    public Map<Boolean, Optional<String>> modifyPassword(String id, String oldPassword, String newPassword, String confirmNewPassword) {
        Map<Boolean, Optional<String>> returnValue = new HashMap<>();
        if (!new BCryptPasswordEncoder().matches(oldPassword, userMapper.selectPasswordById(id))) {
            returnValue.put(false, Optional.of("The old password is not correct"));
        } else if (!newPassword.equals(confirmNewPassword)) {
            returnValue.put(false, Optional.of("The two password are not equals"));
        } else {
            userMapper.updatePassword(id, new BCryptPasswordEncoder().encode(newPassword));
            returnValue.put(true, null);
        }
        return returnValue;
    }
}
