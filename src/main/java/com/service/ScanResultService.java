package com.service;

import com.entity.ScanResult;
import com.mapper.ScanResultMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ScanResultService {

    private final ScanResultMapper scanResultMapper;

    public ScanResultService(ScanResultMapper scanResultMapper) {
        this.scanResultMapper = scanResultMapper;
    }

    public List<ScanResult> getLatestScanResult() {
        String latestScanTime = scanResultMapper.selectLatestScanTime();
        return scanResultMapper.selectResultByScanTime(latestScanTime);
    }

    public List<List<ScanResult>> getHistoryScanResultList() {
        List<String> scanTimeList = scanResultMapper.selectAllScanTime();
        List<List<ScanResult>> historyScanResultList = new LinkedList<>();
        for (String scanTime : scanTimeList) {
            List<ScanResult> singleScanResult = scanResultMapper.selectResultByScanTime(scanTime);
            historyScanResultList.add(singleScanResult);
        }
        return historyScanResultList;
    }
}
