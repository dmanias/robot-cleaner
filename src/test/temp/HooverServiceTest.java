package com.demo.robot_cleaner;

import com.demo.robot_cleaner.model.Hoover;
import com.demo.robot_cleaner.model.HooverRequest;
import com.demo.robot_cleaner.model.HooverResponse;
import com.demo.robot_cleaner.model.Room;
import com.demo.robot_cleaner.service.CleaningService;
import com.demo.robot_cleaner.service.HooverService;
import com.demo.robot_cleaner.service.MovementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class HooverServiceTest {

    @Autowired
    private HooverService hooverService;

    @MockBean
    private MovementService movementService;

    @MockBean
    private CleaningService cleaningService;

    @Test
    void testHooverMovementWithMockService() {
        // Arrange
        int[] roomSize = new int[]{5, 5};
        int[] hooverCoords = new int[]{1, 2};
        List<int[]> patches = List.of(new int[]{1, 0}, new int[]{2, 2}, new int[]{2, 3});
        String instructions = "NNEE";

        HooverRequest request = new HooverRequest(roomSize, hooverCoords, patches, instructions);

        // Mock the MovementService
        doAnswer(invocation -> {
            Hoover hoover = invocation.getArgument(1);
            // Simulate movement (move one step north for each 'N', and one step east for each 'E')
            switch ((char) invocation.getArgument(0)) {
                case 'N':
                    hoover.setY(hoover.getY() + 1);
                    break;
                case 'E':
                    hoover.setX(hoover.getX() + 1);
                    break;
            }
            return null;
        }).when(movementService).moveHoover(anyChar(), any(Hoover.class), any(Room.class));

        // Mock the CleaningService to always return true (dirt cleaned) for the first two calls, then false
        when(cleaningService.cleanDirt(any(Room.class), any(Hoover.class)))
                .thenReturn(true, true, false, false);

        // Act
        HooverResponse response = hooverService.processHooverMovement(request);

        // Assert
        assertArrayEquals(new int[]{3, 4}, response.getCoords());
        assertEquals(2, response.getPatches());

        // Verify that moveHoover was called 4 times (once for each instruction)
        verify(movementService, times(4)).moveHoover(anyChar(), any(Hoover.class), any(Room.class));

        // Verify that cleanDirt was called 4 times (once after each move)
        verify(cleaningService, times(4)).cleanDirt(any(Room.class), any(Hoover.class));
    }
}