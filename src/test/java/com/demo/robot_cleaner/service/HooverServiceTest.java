package com.demo.robot_cleaner.service;

import com.demo.robot_cleaner.exception.OutOfBoundsException;
import com.demo.robot_cleaner.model.HooverRequest;
import com.demo.robot_cleaner.model.HooverResponse;
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

        when(cleaningService.cleanDirt(any(), any())).thenReturn(true, false, true);

        // Act
        HooverResponse response = hooverService.processHooverMovement(request);

        // Assert
        assertEquals(2, response.getPatches());
        assertArrayEquals(new int[]{1, 3}, response.getCoords());
        verify(movementService, times(11)).moveHoover(anyChar(), any(), any());
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
}