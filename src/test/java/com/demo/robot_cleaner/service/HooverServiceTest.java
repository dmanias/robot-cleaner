package com.demo.robot_cleaner.service;

import com.demo.robot_cleaner.exception.OutOfBoundsException;
import com.demo.robot_cleaner.model.Hoover;
import com.demo.robot_cleaner.model.HooverRequest;
import com.demo.robot_cleaner.model.HooverResponse;
import com.demo.robot_cleaner.model.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HooverServiceTest {

    @Mock
    private MovementService movementService;

    @Mock
    private CleaningService cleaningService;

    @InjectMocks
    private HooverService hooverService;

    @Test
    void processHooverMovement_ValidRequest_ReturnsCorrectResponse() {
        // Arrange
        HooverRequest request = new HooverRequest(
                new int[]{5, 5},
                new int[]{1, 2},
                List.of(new int[]{1, 0}, new int[]{2, 2}, new int[]{2, 3}),
                "NNESEESWNWW"
        );

        // Simulate the movement
        when(movementService.moveHoover(anyChar(), any(Hoover.class), any(Room.class))).thenAnswer(invocation -> {
            Hoover hoover = invocation.getArgument(1);
            char direction = invocation.getArgument(0);
            switch (direction) {
                case 'N': hoover.setY(hoover.getY() + 1); break;
                case 'S': hoover.setY(hoover.getY() - 1); break;
                case 'E': hoover.setX(hoover.getX() + 1); break;
                case 'W': hoover.setX(hoover.getX() - 1); break;
            }
            return hoover;
        });

        // Simulate cleaning (2 patches cleaned)
        when(cleaningService.cleanDirt(any(), any())).thenReturn(false, true, false, true, false);

        // Act
        HooverResponse response = hooverService.processHooverMovement(request);

        // Assert
        assertEquals(2, response.getPatches());
        assertArrayEquals(new int[]{1, 3}, response.getCoords());
        verify(movementService, times(11)).moveHoover(anyChar(), any(Hoover.class), any(Room.class));
        verify(cleaningService, times(12)).cleanDirt(any(), any()); // Initial position + 11 moves
    }

    @Test
    void processHooverMovement_InvalidInitialPosition_ThrowsException() {
        // Arrange
        HooverRequest request = new HooverRequest(
                new int[]{5, 5},
                new int[]{6, 6},
                List.of(new int[]{1, 0}),
                "N"
        );

        // Act & Assert
        assertThrows(OutOfBoundsException.class, () -> hooverService.processHooverMovement(request));
    }

    @Test
    void processHooverMovement_InvalidPatchPosition_ThrowsException() {
        // Arrange
        HooverRequest request = new HooverRequest(
                new int[]{5, 5},
                new int[]{1, 1},
                List.of(new int[]{6, 6}),
                "N"
        );

        // Act & Assert
        assertThrows(OutOfBoundsException.class, () -> hooverService.processHooverMovement(request));
    }

    @Test
    void processHooverMovement_NoInstructions_ReturnsInitialPosition() {
        // Arrange
        HooverRequest request = new HooverRequest(
                new int[]{5, 5},
                new int[]{2, 2},
                List.of(new int[]{1, 1}),
                ""
        );

        // Act
        HooverResponse response = hooverService.processHooverMovement(request);

        // Assert
        assertArrayEquals(new int[]{2, 2}, response.getCoords());
        assertEquals(0, response.getPatches());
        verify(movementService, never()).moveHoover(anyChar(), any(), any());
    }
}