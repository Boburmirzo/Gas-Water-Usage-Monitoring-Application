package com.offsidegaming.monitoring.service;

import com.offsidegaming.monitoring.model.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void getUserByNameTest1() {
        //Given
        Long nonExistingUser = 2L;

        //When
        //Then
        assertThrows(RuntimeException.class, () -> userService.getUserById(nonExistingUser));
    }

    @Test
    void getUserByNameTest2() {
        //Given
        Long existingUser = 1L;

        //When
        User user = userService.getUserById(existingUser);

        //Then
        assertEquals(existingUser, user.getId());
    }

    @Test
    void userExists1() {
        //Given
        Long nonExistingUser = 2L;

        //When
        //Then
        assertFalse(userService.userExists(nonExistingUser));
    }

    @Test
    void userExists2() {
        //Given
        Long existingUser = 1L;

        //When
        //Then
        assertTrue(userService.userExists(existingUser));
    }
}