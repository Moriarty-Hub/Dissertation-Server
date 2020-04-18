package com.service;

import com.entity.Target;
import com.mapper.TargetMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TargetService {

    private final TargetMapper targetMapper;
    private List<String> indexList;

    public TargetService(TargetMapper targetMapper) {
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

    public void insertTarget(String target, String targetType) {
        String id = UUID.randomUUID().toString();
        targetMapper.insertTarget(id, target, targetType);
        updateIndexList();
    }
}
