package com.mapper;

import com.entity.ScanResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScanResultMapperTest {

    @Autowired
    private ScanResultMapper scanResultMapper;

    @Test
    public void testSelectAllResults() {
        List<ScanResult> scanResultList = scanResultMapper.selectAllResults();
        assertEquals(2, scanResultList.size());
    }
}
