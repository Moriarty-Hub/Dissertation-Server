package com.service;

import com.entity.Target;
import com.mapper.TargetMapper;
import com.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TargetService {

    private final UserMapper userMapper;
    private final TargetMapper targetMapper;

    public TargetService(UserMapper userMapper, TargetMapper targetMapper) {
        this.userMapper = userMapper;
        this.targetMapper = targetMapper;
    }

    public List<Target> getTargetList() {
        return targetMapper.selectAllTargets();
    }

    public void deleteTarget(String id) {
        targetMapper.deleteTargetById(id);
    }

    public List<Target> getHostTargetList() {
        return targetMapper.selectAllHostTarget();
    }

    public List<Target> getUrlTargetList() {
        return targetMapper.selectAllUrlTarget();
    }

    public Target getTargetById(String id) {
        return targetMapper.selectTargetById(id);
    }

    public boolean updateTarget(String id, String target, String targetType, String department, String owner) {
        if (userMapper.selectUserByUsername(owner) == null) {
            return false;
        }
        targetMapper.updateTarget(id, target, targetType, department, owner);
        return true;
    }

    public boolean insertNewTarget(String target, String targetType, String department, String owner) {
        String id = UUID.randomUUID().toString();
        if (userMapper.selectUserByUsername(owner) == null) {
            return false;
        }
        targetMapper.insertTarget(id, target, targetType, department, owner);
        return true;
    }
}
