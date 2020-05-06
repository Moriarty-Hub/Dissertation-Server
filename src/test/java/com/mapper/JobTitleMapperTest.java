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
public class JobTitleMapperTest {

    @Autowired
    private JobTitleMapper jobTitleMapper;

    @Test
    public void testSelectAllJobTitle() {
        List<String> jobTitleList = jobTitleMapper.selectAllJobTitle();
        assertEquals(9, jobTitleList.size());
        assertTrue(jobTitleList.contains("Administrative Support"));
        assertTrue(jobTitleList.contains("Intern"));
        assertTrue(jobTitleList.contains("Senior Member of Technical Staff"));
        assertTrue(jobTitleList.contains("Unassigned"));
    }
}
