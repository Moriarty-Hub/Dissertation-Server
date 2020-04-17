package com.mapper;

import com.entity.Target;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TargetMapper {

    @Insert("INSERT INTO target(id, target, target_type) VALUES(#{id}, #{target}, #{targetType}")
    void insertTarget(String id, String target, String targetType);

    @Select("SELECT id, target, target_type as targetType FROM target ORDER BY target_type")
    List<Target> selectAllTargets();
}
