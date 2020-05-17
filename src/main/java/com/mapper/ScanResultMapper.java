package com.mapper;

import com.entity.ScanResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ScanResultMapper {

    @Select("SELECT id, target, target_type as targetType, description, scan_time as scanTime FROM scan_result ORDER BY target_type")
    List<ScanResult> selectAllResults();

    @Select("SELECT DISTINCT scan_time FROM scan_result GROUP BY scan_time ORDER BY scan_time DESC")
    List<String> selectAllScanTime();

    @Select("SELECT id, target, target_type as targetType, description, scan_time as scanTime FROM scan_result WHERE scan_time = #{scanTime}")
    List<ScanResult> selectResultByScanTime(String scanTime);

    @Select("SELECT DISTINCT scan_time FROM scan_result ORDER BY scan_time DESC LIMIT 0, 1")
    String selectLatestScanTime();
}
