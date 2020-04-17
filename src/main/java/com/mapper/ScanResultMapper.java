package com.mapper;

import com.entity.ScanResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ScanResultMapper {

    @Select("SELECT id, target, target_type as targetType, description FROM scan_result ORDER BY target_type")
    List<ScanResult> selectAllResults();

}
