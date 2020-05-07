package com.service;

import com.entity.TargetType;
import com.entity.User;
import com.mapper.TargetMapper;
import com.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TargetServiceTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private TargetMapper targetMapper;

    @InjectMocks
    private TargetService targetService;

    @Test
    public void insert_new_target_should_return_false_when_owner_not_exist() {
        when(userMapper.selectUserByUsername("unknownUser")).thenReturn(null);
        boolean result = targetService.insertNewTarget("new target", TargetType.HOST.toString(),
                "Testing", "unknownUser");
        verify(targetMapper, times(0)).
                insertTarget(anyString(), anyString(), anyString(), anyString(), anyString());
        assertFalse(result);
    }

    @Test
    public void insert_new_target_should_insert_target_and_return_true_when_owner_exist() {
        when(userMapper.selectUserByUsername("root")).thenReturn(new User());
        boolean result = targetService.insertNewTarget("new target", TargetType.HOST.toString(),
                "Testing", "root");
        verify(targetMapper, times(1)).
                insertTarget(anyString(), anyString(), anyString(), anyString(), anyString());
        assertTrue(result);
    }

    @Test
    public void update_target_should_return_false_when_owner_not_exist() {
        when(userMapper.selectUserByUsername("unknownUser")).thenReturn(null);
        boolean result = targetService.updateTarget("1", "new target", TargetType.HOST.toString(),
                "Testing", "unknownUser");
        verify(targetMapper, times(0)).
                updateTarget(anyString(), anyString(), anyString(), anyString(), anyString());
        assertFalse(result);
    }

    @Test
    public void update_target_should_insert_target_and_return_true_when_owner_exist() {
        when(userMapper.selectUserByUsername("root")).thenReturn(new User());
        boolean result = targetService.updateTarget("1", "new target", TargetType.HOST.toString(),
                "Testing", "root");
        verify(targetMapper, times(1)).
                updateTarget(anyString(), anyString(), anyString(), anyString(), anyString());
        assertTrue(result);
    }

}
