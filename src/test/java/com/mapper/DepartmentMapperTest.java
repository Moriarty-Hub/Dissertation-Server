package com.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentMapperTest {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Test
    public void testSelectAllDepartmentName() {
        List<String> departmentNameList = departmentMapper.selectAllDepartmentName();
        assertEquals(7, departmentNameList.size());
        assertTrue(departmentNameList.contains("Human Resource"));
        assertTrue(departmentNameList.contains("Lab Operations"));
        assertTrue(departmentNameList.contains("Research & Development"));
    }
}
