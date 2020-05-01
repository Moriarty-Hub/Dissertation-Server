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
    private List<String> indexList;

    public TargetService(UserMapper userMapper, TargetMapper targetMapper) {
        this.userMapper = userMapper;
        this.targetMapper = targetMapper;
        indexList = new ArrayList<>();
        updateIndexList();
    }

    public void updateIndexList() {
        indexList.clear();
        indexList = targetMapper.selectAllTargets().stream().map(Target::getId).collect(Collectors.toList());
    }

    public List<Target> getTargetList() {
        return targetMapper.selectAllTargets();
    }

    public void deleteTarget(String index) {
        targetMapper.deleteTargetById(indexList.remove(Integer.parseInt(index) - 1));
    }

    public void insertTarget(String target, String targetType, String department, String owner) {
        String id = UUID.randomUUID().toString();
        targetMapper.insertTarget(id, target, targetType, department, owner);
        updateIndexList();
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
