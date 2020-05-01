package com.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface JobTitleMapper {
    @Select("SELECT job_title_name FROM job_title")
    List<String> selectAllJobTitle();
}
