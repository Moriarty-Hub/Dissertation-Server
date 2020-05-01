package com.mapper;

import com.entity.Target;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TargetMapper {

    @Insert("INSERT INTO target(id, target, target_type, department, target_owner) VALUES(#{id}, #{target}, #{targetType}, #{department}, #{owner})")
    void insertTarget(String id, String target, String targetType, String department, String owner);

    @Select("SELECT id, target, target_type as targetType, department, target_owner as owner FROM target ORDER BY target_type")
    List<Target> selectAllTargets();

    @Delete("DELETE FROM target WHERE id = #{id}")
    void deleteTargetById(String id);

    @Select("SELECT id, target, target_type as targetType, department, target_owner as owner FROM target WHERE target_type = 'HOST'")
    List<Target> selectAllHostTarget();

    @Select("SELECT id, target, target_type as targetType, department, target_owner as owner FROM target WHERE target_type = 'URL'")
    List<Target> selectAllUrlTarget();

    @Select("SELECT id, target, target_type as targetType, department, target_owner as owner FROM target WHERE id = #{id}")
    Target selectTargetById(String id);

    @Update("UPDATE target SET target = #{target}, target_type = #{targetType}, department = #{department}, target_owner = #{owner} WHERE id = #{id}")
    void updateTarget(String id, String target, String targetType, String department, String owner);
}
