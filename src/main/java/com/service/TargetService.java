package com.service;

import com.entity.Target;
import com.mapper.TargetMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetService {

    private final TargetMapper targetMapper;

    public TargetService(TargetMapper targetMapper) {
        this.targetMapper = targetMapper;
    }

    public List<Target> getTargetList() {
        return targetMapper.selectAllTargets();
    }
}
