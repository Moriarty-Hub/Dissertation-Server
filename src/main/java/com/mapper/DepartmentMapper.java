package com.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DepartmentMapper {

    @Select("SELECT department_name FROM department")
    List<String> selectAllDepartmentName();
}
