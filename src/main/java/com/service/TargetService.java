package com.service;

import com.entity.Target;
import com.mapper.TargetMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TargetService {

    private final TargetMapper targetMapper;
    private final List<String> indexList;

    public TargetService(TargetMapper targetMapper) {
        this.targetMapper = targetMapper;
        indexList = new ArrayList<>();
        targetMapper.selectAllTargets().forEach(target -> indexList.add(target.getId()));
    }

    public List<Target> getTargetList() {
        return targetMapper.selectAllTargets();
    }

    public void deleteTarget(String index) {
        targetMapper.deleteTargetById(indexList.remove(Integer.parseInt(index) - 1));
    }
}
