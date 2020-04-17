package com.service;

import com.entity.ScanResult;
import com.mapper.ScanResultMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScanResultService {

    private final ScanResultMapper scanResultMapper;

    public ScanResultService(ScanResultMapper scanResultMapper) {
        this.scanResultMapper = scanResultMapper;
    }

    public List<ScanResult> getScanResultList() {
        return scanResultMapper.selectAllResults();
    }
}
