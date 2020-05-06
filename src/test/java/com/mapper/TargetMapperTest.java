package com.mapper;

import com.entity.Target;
import com.entity.TargetType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TargetMapperTest {

    @Autowired
    private TargetMapper targetMapper;

    @Before
    public void insertTempTarget() {
        String id = "0b4e8318-2a84-41e3-bbb1-a749e7a13df5";
        String target = "http://internal.wrs.com:8080/departmentdirectory/";
        String targetType = TargetType.URL.toString();
        String department = "Human Resource";
        String targetOwner = "Sherry";
        targetMapper.insertTarget(id, target, targetType, department, targetOwner);
    }

    @After
    public void deleteTempTarget() {
        targetMapper.deleteTargetById("0b4e8318-2a84-41e3-bbb1-a749e7a13df5");
    }

    @Test
    public void should_return_target_when_target_id_exists() {
        Target target = targetMapper.selectTargetById("fe479f93-d40c-4523-80c4-41935df3f93c");
        assertEquals("fe479f93-d40c-4523-80c4-41935df3f93c", target.getId());
        assertEquals("117.50.11.11", target.getTarget());
        assertEquals(TargetType.HOST.toString(), target.getTargetType().toString());
        assertEquals("Lab Operations", target.getDepartment());
        assertEquals("Sean", target.getOwner());
    }

    @Test
    public void should_return_null_when_target_id_not_exists() {
        Target target = targetMapper.selectTargetById("5d3fd82e-95b7-4988-ac9d-4a66f44c7c83");
        assertNull(target);
    }

    @Test
    public void testSelectAllTargets() {
        List<Target> targetList = targetMapper.selectAllTargets();
        assertEquals(6, targetList.size());
    }

    @Test
    public void testSelectAllUrlTarget() {
        List<Target> urlTargetList = targetMapper.selectAllUrlTarget();
        assertEquals(4, urlTargetList.size());
    }

    @Test
    public void testSelectAllHostTarget() {
        List<Target> hostTargetList = targetMapper.selectAllHostTarget();
        assertEquals(2, hostTargetList.size());
    }

    @Test
    public void testInsertTarget() {
        Target target = targetMapper.selectTargetById("2dd8ec56-2a74-4cef-a6b0-20da9a54033e");
        assertNull(target);
        String id = "2dd8ec56-2a74-4cef-a6b0-20da9a54033e";
        String targetName = "47.98.53.171";
        String targetType = TargetType.HOST.toString();
        String department = "Premium Support";
        String targetOwner = "Jeremy";
        targetMapper.insertTarget(id, targetName, targetType, department, targetOwner);
        target = targetMapper.selectTargetById("2dd8ec56-2a74-4cef-a6b0-20da9a54033e");
        assertNotNull(target);
        assertEquals(id, target.getId());
        assertEquals(targetName, target.getTarget());
        assertEquals(targetType, target.getTargetType().toString());
        assertEquals(department, target.getDepartment());
        assertEquals(targetOwner, target.getOwner());
        targetMapper.deleteTargetById("2dd8ec56-2a74-4cef-a6b0-20da9a54033e");
        target = targetMapper.selectTargetById("2dd8ec56-2a74-4cef-a6b0-20da9a54033e");
        assertNull(target);
    }

    @Test
    public void testDeleteTargetById() {
        Target target = targetMapper.selectTargetById("0b4e8318-2a84-41e3-bbb1-a749e7a13df5");
        assertNotNull(target);
        targetMapper.deleteTargetById("0b4e8318-2a84-41e3-bbb1-a749e7a13df5");
        target = targetMapper.selectTargetById("0b4e8318-2a84-41e3-bbb1-a749e7a13df5");
        assertNull(target);
    }

    @Test
    public void testUpdateTarget() {
        Target target = targetMapper.selectTargetById("0b4e8318-2a84-41e3-bbb1-a749e7a13df5");
        assertEquals("http://internal.wrs.com:8080/departmentdirectory/", target.getTarget());
        assertEquals(TargetType.URL.toString(), target.getTargetType().toString());
        assertEquals("Human Resource", target.getDepartment());
        assertEquals("Sherry", target.getOwner());
        String targetName= "http://internal.wrs.com:8080/contact-us/";
        String targetType = TargetType.HOST.toString();
        String department = "IT Client Services";
        String owner = "Tom";
        targetMapper.updateTarget("0b4e8318-2a84-41e3-bbb1-a749e7a13df5", targetName, targetType, department, owner);
        target = targetMapper.selectTargetById("0b4e8318-2a84-41e3-bbb1-a749e7a13df5");
        assertEquals(targetName, target.getTarget());
        assertEquals(targetType, target.getTargetType().toString());
        assertEquals(department, target.getDepartment());
        assertEquals(owner, target.getOwner());
    }
}
