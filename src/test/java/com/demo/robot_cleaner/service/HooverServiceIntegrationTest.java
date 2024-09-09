package com.demo.robot_cleaner.service;

import com.demo.robot_cleaner.model.HooverRequest;
import com.demo.robot_cleaner.model.HooverResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HooverServiceIntegrationTest {

    @Autowired
    private HooverService hooverService;

    @Test
    void testHooverMovementWithActualService() {
        // Arrange
        int[] roomSize = new int[]{5, 5};
        int[] initialHooverCoords = new int[]{1, 2};
        List<int[]> patches = List.of(
                new int[]{1, 0},
                new int[]{2, 2},
                new int[]{2, 3}
        );
        String instructions = "NNESEESWNWW";

        HooverRequest request = new HooverRequest(roomSize, initialHooverCoords, patches, instructions);

        // Act
        HooverResponse response = hooverService.processHooverMovement(request);

        // Assert
        int[] expectedFinalCoords = new int[]{1, 3};
        assertArrayEquals(expectedFinalCoords, response.getCoords(),
                "The final coordinates of the hoover should be [1, 3]");

        assertEquals(1, response.getPatches(),
                "The hoover should have cleaned 1 patch of dirt");
    }

    @Test
    void testHooverMovementWithWallCollision() {
        // Arrange
        int[] roomSize = new int[]{5, 5};
        int[] initialHooverCoords = new int[]{0, 0};
        List<int[]> patches = List.of(
                new int[]{0, 0},
                new int[]{4, 4}
        );
        String instructions = "SWWWW"; // Try to move beyond the west wall

        HooverRequest request = new HooverRequest(roomSize, initialHooverCoords, patches, instructions);

        // Act
        HooverResponse response = hooverService.processHooverMovement(request);

        // Assert
        int[] expectedFinalCoords = new int[]{0, 0};
        assertArrayEquals(expectedFinalCoords, response.getCoords(),
                "The hoover should remain at [0, 0] due to wall collision");

        assertEquals(1, response.getPatches(),
                "The hoover should have cleaned 1 patch of dirt at its starting position");
    }

    @Test
    void testHooverMovementWithNoPatches() {
        // Arrange
        int[] roomSize = new int[]{3, 3};
        int[] initialHooverCoords = new int[]{0, 0};
        List<int[]> patches = List.of();
        String instructions = "NENE";

        HooverRequest request = new HooverRequest(roomSize, initialHooverCoords, patches, instructions);

        // Act
        HooverResponse response = hooverService.processHooverMovement(request);

        // Assert
        assertArrayEquals(new int[]{2, 2}, response.getCoords(),
                "The final coordinates of the hoover should be [2, 2]");
        assertEquals(0, response.getPatches(),
                "The hoover should not have cleaned any patches of dirt");
    }

    @Test
    void testHooverMovementWithAllPatches() {
        // Arrange
        int[] roomSize = new int[]{3, 3};
        int[] initialHooverCoords = new int[]{0, 0};
        List<int[]> patches = List.of(
                new int[]{0, 0},
                new int[]{1, 1},
                new int[]{2, 2}
        );
        String instructions = "NENENE";

        HooverRequest request = new HooverRequest(roomSize, initialHooverCoords, patches, instructions);

        // Act
        HooverResponse response = hooverService.processHooverMovement(request);

        // Assert
        assertArrayEquals(new int[]{2, 2}, response.getCoords(),
                "The final coordinates of the hoover should be [2, 2]");
        assertEquals(3, response.getPatches(),
                "The hoover should have cleaned all 3 patches of dirt");
    }
}